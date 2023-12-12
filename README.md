# Ohjelmistotuotanto syksy 2023
## Matleena Kankaanpää, Elmo Erla, Mikko Hänninen, Nikita Nossenko
Kodin automaatiojärjestelmä koneoppimista hyödyntäen.

Visio<br>
Visiomme on tehdä älykodista helppokäyttöisempi ja saavutettavampi jokaiselle käyttäjälle. Haluamme tarjota keskitetyn ratkaisun, joka yksinkertaistaa ja automatisoi kodin laitteiden hallintaa.<br>
Tavoitteemme on luoda yhtenäinen sovellus, joka yhdistää useiden älylaitteiden ohjauksen ja seurannan, tehostaen niiden käyttöä koneoppimisen avulla automatisoitujen rutiinien kautta.<br><br>
Kehitysympäristö ja riippuvuudet<br>
Automaatio sovellus on kehitetty Java-kielellä ja se käyttää seuraavia kirjastoja:<br>
● Jackson: JSON-käsittelyyn.<br>
● Hibernate: ORM-työkalu tietokannan ja Java-objektien välille.<br>
● MySQLConnector: Tietokantayhteyksien muodostamiseen.<br>
● JavaFX: Graafisen käyttöliittymän luomiseen.<br>
● JakartaPersistence: JPA-standardiin.<br>
● JUnit: Yksikkötestaukseen.<br>
● TestFX: End-to-end -testaukseen.<br>
● JBcrypt: Salasanojen salaamiseen.<br>
● ControlsFXjaGemsFX: Graafiseen käyttöliittymään lisäkomponentteja ja ominaisuuksia.<br>

Asennus- ja konfigurointiohjeet<br>
Riippuvuuksien asentaminen:<br>
Käytä Mavenia asentaaksesi kaikki tarvittavat riippuvuudet: -mvn clean install<br>

Tietokannan konfigurointi:<br>
Varmista, että sinulla on MySQL-palvelin käynnissä ja konfiguroi tietokantayhteytesi sovelluksen konfiguraatiotiedostossa (persistence.xml).<br>

Vaihda tämä rivin arvokenttään ”drop-and-create” kun ajat sovelluksen ensimmäisen kerran. Tämä luo sinulle tarvittavan tietokannan.<br>
property name="jakarta.persistence.schema-generation.database.action" value="none"

Kun olet ajanut ohjelman, vaihda kentän arvo takaisin ”none”. Näin tietokanta on luotu ja se on valmiina käytettäväksi.<br>
Käynnistä sovellus: -mvn javafx:run<br>

Työtuntien kirjanpito:<br>
https://tinyurl.com/ryhma8otp2

Trello:<br>
https://trello.com/w/ohtugroup8

Project Planning Document:<br>
https://metropoliafi-my.sharepoint.com/:w:/g/personal/mikkoolh_metropolia_fi/EUJrjHKrXwlPhICn0DhlaPABUFJQvIkn1jvqfzJj2F9jow?rtime=t_j6hbLC20g
