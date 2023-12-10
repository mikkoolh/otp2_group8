package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * The DeviceDAO class represents a DAO (Data Access Object)
 * for carrying out database operations related to the Device entity.
 *
 * @author Nikita Nossenko, Elmo Erla
 * @version 1.0
 */

public class DeviceDAO implements IDAO {

    /**
     * Adds a new device to the database.
     *
     * @param device A new device
     */
    @Override
    public void addObject(Object device) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            em.persist((Device) device);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Adds a new device to the database and returns it.
     *
     * @param object    A new device
     * @return          The new device that was added to the database
     */
    public Device addAndReturnObject(Object object) {
        Device savedDevice;

        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            savedDevice = em.merge((Device) object);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return savedDevice;
    }

    /**
     * Deletes a device from the database.
     *
     * @param id    The id of the device to be deleted
     */
    @Override
    public void deleteObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            Device device = em.find(Device.class, id);
            if (device != null) {
                em.remove(device);
                System.out.println("Device " + id + " deleted");
            } else {
                throw new IllegalArgumentException("Device with id  " + id + " was not found");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Fetches a device by its ID
     *
     * @param id    ID of the device
     * @return      Device object
     */
    public Device getObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            Device device = em.find(Device.class, id);
            em.getTransaction().commit();
            return device;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Fetches a device by its name
     *
     * @param name  The name of the device
     * @return      Device object or null if not found
     */
    @Override
    public Device getObject(String name) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            TypedQuery<Device> query = em.createQuery(
                    "SELECT d FROM Device d WHERE d.name = :name", Device.class);
            query.setParameter("name", name);
            Device device = query.getSingleResult();
            em.getTransaction().commit();
            return device;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Fetches all devices
     *
     * @return      A list of Device objects
     */
    public List<Device> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            TypedQuery<Device> query = em.createQuery("SELECT d FROM Device d", Device.class);
            List<Device> devices = query.getResultList();
            em.getTransaction().commit();
            return devices;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Fetches all devices where automation is enabled
     *
     * @return      A list of devices where automation is enabled
     */
    public List<Device> getAutoDevices() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            TypedQuery<Device> query = em.createQuery("SELECT d FROM Device d WHERE d.automation = true", Device.class);
            List<Device> devices = query.getResultList();
            em.getTransaction().commit();
            return devices;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Fetches all devices associated with a specific user
     *
     * @param userName      The username of the user whose devices will be fetched
     * @return              A list of devices associated with the user or null of not found
     */
    public List<Device> getDevicesByUserName(String userName) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            TypedQuery<Device> query = em.createQuery("SELECT d FROM Device d WHERE d.userName = :userName", Device.class);
            query.setParameter("userName", userName);
            List<Device> devices = query.getResultList();
            em.getTransaction().commit();
            return devices;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Deletes all devices from the database
     */
    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        String sql = "DELETE FROM Device";
        Query query = em.createQuery(sql);

        int deletedCount = query.executeUpdate();

        em.getTransaction().commit();

        System.out.println("Poistettu " + deletedCount + " laitetta.");

    }

    /**
     * Deletes a device with a specific ID from the database.
     *
     * @param id                        The ID of the device to be deleted.
     * @throws IllegalArgumentException Exception that will be thrown if a device with the given ID is not found.
     */
    public void deleteDevice(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            Device device = em.find(Device.class, id);
            if (device != null) {
                em.remove(device);
            } else {
                throw new IllegalArgumentException("device with id  " + id + " was not found");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Updates the name of a device.
     *
     * @param deviceId  The ID of the device to update.
     * @param newName   The new name for the device.
     */
    public void updateDevice(int deviceId, String newName) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        Device device = em.find(Device.class, deviceId);

        if (device != null) {
            device.setName(newName);
            em.merge(device);
        }
        em.getTransaction().commit();
    }

    /**
     * Updates the model code of a device.
     *
     * @param deviceId         The ID of the device to update.
     * @param newModelCode      The new model code for the device
     */
    public void updateModelCode(int deviceId, String newModelCode) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        Device device = em.find(Device.class, deviceId);
        if (device != null) {
            device.setModelCode(newModelCode);
            em.merge(device);
        }
        em.getTransaction().commit();
    }

    /**
     * Updates the deviceGroupId of a device.
     *
     * @param deviceId          The ID of the device to update.
     * @param newDeviceGroupId  The new deviceGroupId for the device.
     */
    public void updateDeviceGroup(int deviceId, int newDeviceGroupId) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        Device device = em.find(Device.class, deviceId);
        DeviceGroup newDeviceGroup = em.find(DeviceGroup.class, newDeviceGroupId);

        if (device != null && newDeviceGroup != null) {
            device.setDeviceGroup(newDeviceGroup);
            em.merge(device);
        } else {
            if (device == null) {
                System.out.println("Device with ID " + deviceId + " was not found.");
            }
            if (newDeviceGroup == null) {
                System.out.println("DeviceGroup with ID " + newDeviceGroupId + " was not found.");
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Updates the state of the device. If the device was previously on, it will be turned off.
     * If the device was off, it will be turned on.
     *
     * @param deviceId  The ID of the device to update
     */
    public void updateDeviceOnOff(int deviceId){
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        Device device = em.find(Device.class, deviceId);

        if (device != null) {
            device.switchOnOff();
            em.merge(device);
        } else {
            if (device == null) {
                System.out.println("Device with ID " + deviceId + " was not found.");
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Updates the usage data of a device.
     *
     * @param deviceId      The ID of the device to update.
     * @param newUsageData  The new usage data for the device.
     */
    public void updateUsageData(int deviceId, long newUsageData) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        Device device = em.find(Device.class, deviceId);
        if (device != null) {
            device.setUsageData(newUsageData);
            em.merge(device);
        } else {
            System.out.println("Device with ID " + deviceId + " was not found.");
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Updates the automation setting of the device. If automation was previously enabled, it will be disabled.
     * If automation was disabled, it will be enabled.
     *
     * @param deviceID  The ID of the device to update
     */
    public void updateAutomation(int deviceID) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        Device device = em.find(Device.class, deviceID);

        if (device != null) {
            if (device.isAutomation() != true) {
                device.setAutomation(true);
                em.merge(device);
                System.out.println("Laitteen " + device.getDeviceID() + " Automation arvo muutettu trueksi");
            } else {
                device.setAutomation(false);
                em.merge(device);
                System.out.println("Laitteen " + device.getDeviceID() + " Automation arvo muutettu falseksi");
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Deletes all devices associated with a specific username.
     *
     * @param username  The username of the user whose devices should be deleted.
     */
    public void deleteDevicesByUsername(String username) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("DELETE FROM Device d WHERE d.userName = :username");
            query.setParameter("username", username);

            int count = query.executeUpdate();
            System.out.println("Deleted " + count + " devices");

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error deleting devices from " + username);
            throw e;
        } finally {
            em.close();
        }
    }
}
