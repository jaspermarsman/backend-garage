#Backend Garage
Deze backend applicatie is ontwikkeld in het Spring Boot Framework.
De applicatie geeft garagehouders een systeem om klanten, voertuigen, afspraken en voorraad te beheren. 

##Installatie
###Benodigdheden:

- Java 11
- PostgreSQL database

###Stappen

1. Download en open het project in je favourite IDE. Of kloon de repo via onderstaande URL om zeker te zijn van toekomstige updates. 
> https://github.com/jaspermarsman/backend-garage.git 
2. Download en installeer PosrgreSQL via onderstaande URL.
> https://www.postgresql.org/download/
3. In dit project is onderstaande configuratie gebruikt voor PostgreSQL. 
- spring.sql.init.platform=postgres
- spring.datasource.url=jdbc:postgresql://localhost:5432/garage
- spring.datasource.username=garage
- spring.datasource.password=garage

Wil je een andere configuratie gebruiken, dan pas je dit aan in het **application.properties** bestand.

4. Start de applicatie

##Autorisatie
Om een bearer token te krijgen gebruik je de endpoint http://localhost:8080/authenticate.
Maak voor deze endpoint gebruik van "Basic Authentication" en geef onderstaande in de body mee.
    
    {
        "username": "admin",
        "password": "password"
    }

Je krijgt dan een Bearer token teruggestuurd die je moet gebruiken voor alle andere endpoints. 