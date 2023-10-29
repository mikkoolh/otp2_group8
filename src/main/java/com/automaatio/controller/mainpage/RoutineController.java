package com.automaatio.controller.mainpage;

import com.automaatio.components.*;
import com.automaatio.components.buttons.CancelIconCreator;
import com.automaatio.components.buttons.DeleteIconCreator;
import com.automaatio.components.buttons.EditIconCreator;
import com.automaatio.components.buttons.SaveIconCreator;
import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.DatabaseTool;
import com.automaatio.utils.ErrorMessageHandler;
import com.automaatio.utils.RoutineUtils;
import com.dlsc.gemsfx.TimePicker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.controlsfx.control.ToggleSwitch;
import java.net.URL;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Controller for the routines listing
 *
 * @author Nikita Nossenko
 * @author Matleena Kankaanpää
 *
 * 1.10.2023
 */

public class RoutineController implements Initializable {

    @FXML
    private TextArea routineNameField;

    public RoutineController() {}

    CacheSingleton cache = CacheSingleton.getInstance();

    RoutineDAO routineDAO = new RoutineDAO();
    EventTimeDAO eventTimeDAO = new EventTimeDAO();

    @FXML
    private VBox routineVBox;

    @FXML
    private Button addRoutineButton, deleteAllButton, saveButton;

    @FXML
    private Text noRoutinesText, formTitle;

    @FXML
    private Label errorMessageField;

    @FXML
    private ScrollPane routineScrollPane;

    @FXML
    private VBox addRoutineForm;

    @FXML
    private GridPane formGrid;
    private List<Routine> routines;
    private final int ID = cache.getDevice().getDeviceID();

    private final Map<Routine, ToggleSwitch> toggleSwitches = new LinkedHashMap<>();
    private final Map<Button, Routine> deleteButtons = new LinkedHashMap<>();
    private final RoutineUtils util = new RoutineUtils();
    private final WeekdayDAO weekdayDAO = new WeekdayDAO();
    private final Map<Weekday, CheckBox> weekdayCheckBoxes = new LinkedHashMap<>();
    private Label weekdayTooltip;
    private TimePicker startTimePicker, endTimePicker;
    private List<Weekday> weekdays;
    private boolean noRoutinesToShow;
    private final String FONT = "System";
    private final int FONT_SIZE_TEXT = 24;
    private ErrorMessageHandler errorHandler;

    private Locale currentLocale = new Locale("fi", "FI"); // tähän currentlocale

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox.setVgrow(routineVBox, Priority.ALWAYS);
        VBox.setVgrow(routineScrollPane, Priority.ALWAYS);
        errorHandler = new ErrorMessageHandler();
        errorMessageField.getStyleClass().add("error-text");
        noRoutinesToShow = true;
        try {
            routines = util.sortByTime(fetchRoutines()); // Sort fetched routines by time
            DatabaseTool.resetWeekdays();
            weekdays = weekdayDAO.getAll();
            initializeForm();
            loadRoutines();
        } catch (Exception e) {
            e.printStackTrace();
            displayErrorText();
        }
        routineNameField.setText(cache.getDevice().getName());
        routineScrollPane.setStyle("-fx-background-color:transparent;");
        hideForm();
    }

    private void loadRoutines() {
        routineVBox.getChildren().clear();
        errorHandler.hideErrorMessage(errorMessageField);
        noRoutinesToShow = true;
        LinkedHashMap<String, ArrayList<Routine>> map = new LinkedHashMap<>();

        boolean fetchedOk = false;

        try {
            // Fetch routines from db
            map = util.getRoutinesByWeekday(weekdays, routines);
            fetchedOk = true;
        } catch (Exception e) {
            e.printStackTrace();
            displayErrorText();
        }

        // Display routines if fetched successfully
        if (fetchedOk) {

            // For keeping track of whether there are any routines saved or not
            boolean noSavedRoutines = true;

            for (Map.Entry<String, ArrayList<Routine>> entry : map.entrySet()) {
                String weekday = entry.getKey();
                ArrayList<Routine> routines = entry.getValue();

                if (!routines.isEmpty()) {
                    noSavedRoutines = false; // Set to false
                    routineVBox.setAlignment(Pos.TOP_LEFT);
                    deleteAllButton.setVisible(true);
                    HBox splitBox = new HBox();
                    Label weekdayLabel = new WeekdayLabel(weekday).create();
                    weekdayLabel.setMinWidth(60);
                    noRoutinesToShow = false;

                    VBox routinesContainer = new VBox(); // Routines are listed here
                    routinesContainer.setSpacing(10); // Spacing between routines on the same weekday
                    HBox.setHgrow(routinesContainer, Priority.ALWAYS); // Make routine row fill up available space
                    splitBox.getChildren().addAll(weekdayLabel, routinesContainer);

                    for (Routine routine : routines) {
                        HBox routineRow = createRoutineRow(routine);
                        splitBox.getChildren().add(routineRow);
                        routinesContainer.getChildren().add(routineRow);
                    }
                    routineVBox.getChildren().add(splitBox);
                    routineVBox.setSpacing(20);  // Spacing between weekday groups
                }
            }

            /*
             If the database connection was ok but there were no saved routines,
             display the "You don't have any routines" text
             */
            if (noSavedRoutines) {
                noRoutinesText.setVisible(true);
                routineVBox.getChildren().add(noRoutinesText);
                routineVBox.setAlignment(Pos.CENTER);
            }
        }
    }

    private HBox createRoutineRow(Routine routine) {
        Label startTime = new Label(util.getFormattedTime(routine.getEventTime().getStartTime()));
        Label endTime = new Label(util.getFormattedTime(routine.getEventTime().getEndTime()));

        Button editButton = (new EditIconCreator()).create();
        Button saveButton = (new SaveIconCreator()).create();
        Button cancelButton = (new CancelIconCreator()).create();
        Button deleteButton = (new DeleteIconCreator()).create();

        // Automation toggle
        ToggleSwitch toggle = getToggleSwitch(routine);

        // Container for start and end times
        GridPane clockTimes = new GridPane();
        clockTimes.add(startTime, 0, 0);
        clockTimes.add(new Label("-"), 1, 0);
        clockTimes.add(endTime, 2, 0);

        HBox timeContainer = new HBox(clockTimes); // Wrapper box
        timeContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(timeContainer, Priority.ALWAYS);
        clockTimes.setHgap(5);

        // Time pickers (editing mode)
        TimePicker newStartTime = (new TimeSelector()).getTimePicker();
        TimePicker newEndTime = (new TimeSelector()).getTimePicker();
        newStartTime.setTime(routine.getEventTime().getStartTime().toLocalTime());
        newEndTime.setTime(routine.getEventTime().getEndTime().toLocalTime());

        // Grid containing time pickers (editing mode)
        GridPane newClockTimes = (new TimeSelectorGrid()).create(newStartTime, newEndTime);

        // Store time pickers in an array
        List<TimePicker> editingTimePickers = Arrays.asList(newStartTime, newEndTime);

        // Change listeners for time pickers (editing mode)
        for (TimePicker timePicker : editingTimePickers) {
            timePicker.timeProperty().addListener((observable, oldValue, newValue) -> {
                editButton.setDisable(!validateTimeInput(newStartTime, newEndTime));
            });
        }

        // Container for edit, save, delete and cancel buttons
        HBox routineRowButtons = new HBox(10, cancelButton, deleteButton, editButton);
        HBox routineRow = new HBox(10, timeContainer, toggle, routineRowButtons);
        HBox.setHgrow(routineRow, Priority.ALWAYS);
        routineRow.setStyle("-fx-background-color: #e1e1e1; -fx-background-radius: 4;");

        // Delete button
        deleteButton.setOnAction(deleteRoutine);
        deleteButtons.put(deleteButton, routine);
        deleteButton.setVisible(false);

        // Cancel button
        cancelButton.setVisible(false);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteButton.setVisible(!deleteButton.isVisible());
                cancelButton.setVisible(!cancelButton.isVisible());
                timeContainer.getChildren().clear();
                timeContainer.getChildren().add(clockTimes);
                routineRowButtons.getChildren().remove(saveButton);
                routineRowButtons.getChildren().add(editButton);
            }
        });

        // Edit button event handler
        editButton.addEventHandler(ActionEvent.ACTION, (e)-> {
            deleteButton.setVisible(true);
            cancelButton.setVisible(true);
            timeContainer.getChildren().clear();
            timeContainer.getChildren().add(newClockTimes);
            routineRowButtons.getChildren().remove(editButton);
            routineRowButtons.getChildren().add(saveButton);
        });

        // Save button event handler
        saveButton.addEventHandler(ActionEvent.ACTION, (e)-> {
            System.out.println("save clicked");
            boolean updateSuccessful = false;

            try {
                // New times
                LocalDateTime updatedStartTime = newStartTime.getTime().atDate(LocalDate.now());
                LocalDateTime updatedEndTime = newEndTime.getTime().atDate(LocalDate.now());

                // Get weekday of the current routine
                Weekday wd = (Weekday) weekdayDAO.getObject(routine.getEventTime().getWeekday().getWeekdayId());

                // Create a new event time with the current weekday + new times
                EventTime newEventTime = eventTimeDAO.addAndReturnObject(new EventTime(updatedStartTime, updatedEndTime, wd));
                routineDAO.updateTime(routine.getRoutineID(), newEventTime, toggle.isSelected());
                updateSuccessful = true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            if (updateSuccessful) {
                loadRoutines();
                deleteButton.setVisible(false);
                cancelButton.setVisible(false);
                timeContainer.getChildren().clear();
                timeContainer.getChildren().add(clockTimes);
                routineRowButtons.getChildren().remove(saveButton);
                routineRowButtons.getChildren().add(editButton);
                errorHandler.hideErrorMessage(errorMessageField);
            } else {
                System.out.println("up8 failed");
                errorHandler.showErrorMessage(resourceBundle.getString("generalErrorTxt"), errorMessageField);
            }
        });

        Label[] labels = new Label[] {startTime, endTime};
        for (Label label : labels) {
            label.setFont(new Font(FONT, FONT_SIZE_TEXT));
        }

        routineRow.setPadding(new Insets(10));
        routineRow.setAlignment(Pos.CENTER_RIGHT);
        return routineRow;
    }

    // Fetches routines from the database
    private List<Routine> fetchRoutines() {
        return routineDAO.getRoutinesByDeviceId(ID);
    }

    // Creates a toggle switch with an event handler to change the state of the current routine
    private ToggleSwitch getToggleSwitch(Routine routine) {
        ToggleSwitch toggle = new ToggleSwitch();
        toggle.setSelected(routine.getAutomated());
        toggle.setText(resourceBundle.getString("automateTxt"));

        toggle.setOnMouseClicked(mouseEvent -> {
            routineDAO.toggleOnOff(routine.getRoutineID(), routine.getAutomated());
        });

        toggleSwitches.put(routine, toggle);
        return toggle;
    }

    // Shows a confirmation popup when the "Delete routine" button is clicked
    private final EventHandler<ActionEvent> deleteRoutine = new EventHandler<>() {
        public void handle(ActionEvent event) {
            Routine routineToDelete = deleteButtons.get((Button) event.getTarget());

            // Define the popup
            Alert alert = new Alert(Alert.AlertType.NONE, null, ButtonType.OK,
                    ButtonType.CANCEL);
            EventTime time = routineToDelete.getEventTime();
            alert.setContentText(getConfirmDeleteRoutineTxt(time.getWeekday().getName(), util.getFormattedTime(time.getStartTime()), util.getFormattedTime(time.getStartTime())));
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(resourceBundle.getString("deleteBtnTxt"));
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText(resourceBundle.getString("cancelBtnTxt"));

            // Respond to user input
            if (alert.showAndWait().get() == ButtonType.OK) {
                try {
                    routineDAO.deleteObject(routineToDelete.getRoutineID());
                    errorHandler.hideErrorMessage(errorMessageField);
                    loadRoutines();
                } catch(Exception e) {
                    e.printStackTrace();
                    errorHandler.showErrorMessage(resourceBundle.getString("generalErrorTxt"), errorMessageField);
                }
            }
        }
    };

    private String getConfirmDeleteRoutineTxt(String weekday, String startTime, String endTime) {
        Object[] args = { cache.getDevice().getName(), weekday, startTime, endTime };
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
        formatter.applyPattern(resourceBundle.getString("confirmDeleteRoutineTemplate"));
        return formatter.format(args);
    }

    @FXML
    private void hideForm() {
        addRoutineForm.setVisible(false);
        addRoutineForm.setManaged(false);
        addRoutineButton.setVisible(true);
        noRoutinesText.setVisible(noRoutinesToShow);
    }

    @FXML
    private void showForm() {
        addRoutineForm.setVisible(true);
        addRoutineForm.setManaged(true);
        addRoutineButton.setVisible(false);
        noRoutinesText.setVisible(!noRoutinesText.isVisible());

        // Reset checkboxes
        for (CheckBox checkBox : weekdayCheckBoxes.values()) {
            checkBox.setSelected(false);
        }
    }

    @FXML
    private void handleSaveRoutine() {

        // Get start and end times from time pickers
        LocalDateTime startTime = startTimePicker.getTime().atDate(LocalDate.now());
        LocalDateTime endTime = endTimePicker.getTime().atDate(LocalDate.now());

        // Iterate through weekday checkboxes
        for(Map.Entry<Weekday, CheckBox> entry : weekdayCheckBoxes.entrySet()){
            if (entry.getValue().isSelected()) {
                // If a weekday is selected, create a new routine for that day

                try {
                    // Get the corresponding weekday from the database
                    Weekday selectedWeekday = (Weekday) weekdayDAO.getObject(entry.getKey().getWeekdayId());
                    // Create event time
                    EventTime eventTime = eventTimeDAO.addAndReturnObject(new EventTime(startTime, endTime, selectedWeekday));

                    User user = cache.getUser();
                    Device device = cache.getDevice();

                    /*
                     Save the routine to the database.
                     Feature is null for now, routines added by users are automatically on.
                     */
                    Routine routine = new Routine(user, device, null, eventTime, true);
                    routineDAO.addObject(routine);
                    System.out.println("saved routine");
                    loadRoutines();
                    hideForm();
                } catch (Exception e) {
                    e.printStackTrace();
                    errorHandler.showErrorMessage(resourceBundle.getString("generalErrorTxt"), errorMessageField);
                }
            }
        }
    }

    private void initializeForm() {
        addRoutineForm.setPadding(new Insets(10));
        addRoutineForm.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
        formTitle.setText(getFormTitle());

        // Weekday checkboxes
        for (Weekday weekday : weekdayDAO.getAll()) {
            weekdayCheckBoxes.put(weekday, new CheckBox(weekday.getName()));
        }
        VBox weekdaysVBox = new VBox();
        weekdaysVBox.getChildren().addAll(weekdayCheckBoxes.values());
        weekdaysVBox.setSpacing(5);

        // Time pickers
        startTimePicker = (new TimeSelector()).getTimePicker();
        endTimePicker = (new TimeSelector()).getTimePicker();
        GridPane clockTimes = (new TimeSelectorGrid()).create(startTimePicker, endTimePicker);
        clockTimes.add(weekdaysVBox, 1, 2);
        formGrid.add(clockTimes, 0,0);

        // Tooltip
        weekdayTooltip = new Label(resourceBundle.getString("selectAtLeastOneWeekdayTxt"));
        weekdayTooltip.setStyle("-fx-text-fill: #5E5E5E; -fx-font-family: Verdana; -fx-font-style: italic; -fx-font-size: 11px;");
        clockTimes.add(weekdayTooltip, 1, 3);

        // Prevent saving a routine where the start and end times are the same (default values)
        saveButton.setDisable(true);

        List<TimePicker> timePickers = Arrays.asList(startTimePicker, endTimePicker);

        // Change listeners for time pickers
        for (TimePicker timePicker : timePickers) {
            timePicker.timeProperty().addListener((observable, oldValue, newValue) -> {
                checkSaveButtonState();
            });
        }

        // Change listeners for weekday checkboxes
        for (Map.Entry<Weekday, CheckBox> set : weekdayCheckBoxes.entrySet()) {
            set.getValue().selectedProperty().addListener((observable, oldValue, newValue) -> {
                checkSaveButtonState();
            });
        }
    }

    /*
    Applies the name of the cached device to the
    "Add a new routine for _" template in the current language
     */
    private String getFormTitle() {
        Object[] args = { cache.getDevice().getName() };
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
        formatter.applyPattern(resourceBundle.getString("addRoutineTemplate"));
        return formatter.format(args);
    }

    @FXML
    private void deleteAll() {
        try {
            // Define the popup
            Alert alert = new Alert(Alert.AlertType.NONE, null, ButtonType.OK,
                    ButtonType.CANCEL);
            String alertTxt = getDeleteAllConfirmation(fetchRoutines().size());
            alert.setContentText(alertTxt);
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(resourceBundle.getString("deleteAllBtnTxt"));
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText(resourceBundle.getString("cancelBtnTxt"));

            // Respond to user input
            if (alert.showAndWait().get() == ButtonType.OK) {
                routineDAO.deleteByDeviceAndUser(cache.getDevice(), cache.getUser());
                loadRoutines();
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorHandler.showErrorMessage(resourceBundle.getString("generalErrorTxt"), errorMessageField);
        }
    }

    /*
    Applies the name of the cached device and routine count to the
    "Delete _ routines for _?" template in the current language
     */
    private String getDeleteAllConfirmation(int amount) {
        Object[] args = { amount, cache.getDevice().getName() };
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
        formatter.applyPattern(resourceBundle.getString("confirmDeleteAllRoutinesTemplate"));
        return formatter.format(args);
    }

    private void displayErrorText() {
        errorHandler.showErrorMessage(resourceBundle.getString("routinesLoadErrorTxt"), errorMessageField);
        noRoutinesText.setVisible(false); // Don't display both disclaimers
    }

    // Checks if the values of two time pickers are in successive order
    private boolean validateTimeInput(TimePicker startTime, TimePicker endTime) {
        boolean timeInputOk = util.compareTimes(startTime.getTime(), endTime.getTime());

        if (timeInputOk) {
            startTime.getStyleClass().remove("inputErrorState");
            endTime.getStyleClass().remove("inputErrorState");
        } else {
            startTime.getStyleClass().add("inputErrorState");
            endTime.getStyleClass().add("inputErrorState");
        }

        return timeInputOk;
    }

    // Returns true if at least one checkbox is selected
    private boolean validateWeekdaySelection() {
        validateTimeInput(startTimePicker, endTimePicker);
        for (Map.Entry<Weekday, CheckBox> set : weekdayCheckBoxes.entrySet()) {
            if (set.getValue().isSelected()) {
                weekdayTooltip.setVisible(false);
                return true;
            }
        }
        weekdayTooltip.setVisible(true);
        return false;
    }

    // Controls the clickability of the save button depending on input validations
    private void checkSaveButtonState() {
        boolean weekdaySelectionOk = validateWeekdaySelection();
        boolean timeInputOk = validateTimeInput(startTimePicker, endTimePicker);
        saveButton.setDisable(!timeInputOk || !weekdaySelectionOk);
    }
}