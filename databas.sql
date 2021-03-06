DROP TABLE MenuItemHasIngredient;
DROP TABLE Ingredient;
DROP TABLE Pass;
DROP TABLE Event;
DROP TABLE Lunch;
DROP TABLE WeekDay;
DROP TABLE OrderMenuItem;
DROP TABLE MenuItem;
DROP TABLE Bill;
DROP TABLE Employee;
DROP TABLE Tables;
DROP TABLE EmployeePass;

CREATE TABLE Tables(
    TableNr INTEGER NOT NULL PRIMARY KEY
);

CREATE TABLE Employee(
    Name VARCHAR(55) NOT NULL PRIMARY KEY
);

CREATE TABLE Bill (
    ID INTEGER NOT NULL PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    Status VARCHAR(40),
    TableNr INTEGER,
    EmployeeID INTEGER,
    Time VARCHAR(40)
);

CREATE TABLE MenuItem (
    Name VARCHAR(128) NOT NULL PRIMARY KEY,
    Price INTEGER,
    Type VARCHAR(55),
    Description  VARCHAR(200),
    PrepTime DOUBLE
);

CREATE TABLE OrderMenuItem(
    OrderItemNr INTEGER NOT NULL
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    Comment VARCHAR(55),
    MenuItemName VARCHAR(128),
    BillNr INTEGER,
    PRIMARY KEY (OrderItemNr)
);

CREATE TABLE WeekDay(
    ID SMALLINT NOT NULL PRIMARY KEY,
    Name VARCHAR(8) NOT NULL
);

CREATE TABLE Lunch(
    ID INTEGER NOT NULL PRIMARY KEY,
    Name VARCHAR(128),
    WeekDay SMALLINT,
    Description  VARCHAR(200),
    FOREIGN KEY (WeekDay) REFERENCES WeekDay(ID)
);

CREATE TABLE Event (
    ID INTEGER NOT NULL PRIMARY KEY,
    Name VARCHAR(20),
    Clock VARCHAR(50),
    Date DATE,
    Image VARCHAR(500), 
    OpeningAct VARCHAR(20),
    Description VARCHAR(50)
);

CREATE TABLE Pass (
    ID INTEGER NOT NULL,
    Pass INTEGER,
    WeekDay INTEGER,
    WeekNr INTEGER,
    PRIMARY KEY(Id)
);

CREATE TABLE EmployeePass (
    ID INTEGER NOT NULL PRIMARY KEY,
    EmployeeName VARCHAR(55),
    PassID INTEGER
);

CREATE TABLE Ingredient (
    Name VARCHAR(55) NOT NULL PRIMARY KEY,
    Amount INTEGER
);

CREATE TABLE MenuItemHasIngredient (
    ID INTEGER not null,
    IngredientName VARCHAR(55),
    MenuItemName VARCHAR(128),
    Quantity INT,
    PRIMARY KEY (Id)
);

/* INSERTS */

/* Weekday */
INSERT INTO WeekDay VALUES (0, 'Måndag');
INSERT INTO WeekDay VALUES (1, 'Tisdag');
INSERT INTO WeekDay VALUES (2, 'Onsdag');
INSERT INTO WeekDay VALUES (3, 'Torsdag');
INSERT INTO WeekDay VALUES (4, 'Fredag');

/* Day */ 
INSERT INTO Lunch VALUES (0, 'Kycklinglasagnesoppa', 0, 'Perfektion');
INSERT INTO Lunch VALUES (1, 'Gröt med mjölk på', 0, 'God julmat');
INSERT INTO Lunch VALUES (2, 'Hamburgare med beasås', 0, 'Bra Combo');
INSERT INTO Lunch VALUES (3, 'Kallops med pommes', 1, 'Pommes är gott iaf');
INSERT INTO Lunch VALUES (4, 'Pizza med apelsin på', 1, 'Svensk klassiker');
INSERT INTO Lunch VALUES (5, 'Friterad Lax', 1, 'Friterat är gott!');
INSERT INTO Lunch VALUES (6, 'Grillad morot', 2, 'Grillat är gott!');
INSERT INTO Lunch VALUES (7, 'Spaghetti med lök', 2, 'Bättre än fisk iaf');
INSERT INTO Lunch VALUES (8, 'Gustafskorvmacka', 2, 'Som en äkta dalmas');
INSERT INTO Lunch VALUES (9, 'Råbiff', 3, 'Fransk klassiker');
INSERT INTO Lunch VALUES (10, 'Grillad Sushi', 3, 'Japanskt');
INSERT INTO Lunch VALUES (11, 'Kalops med fish and chips', 3, 'Svengelska');
INSERT INTO Lunch VALUES (12, 'Pommeskryddad plankstek', 4, 'Eh');
INSERT INTO Lunch VALUES (13, 'Osaltad Fisksoppa', 4, 'Mums');
INSERT INTO Lunch VALUES (14, 'Kebarulle', 4, 'Riktigt gott');

/* Menuitem */
INSERT INTO Menuitem VALUES ('Toast skagen', 195, 'Förrätt', 'Med handskalade räkor på vitt bröd', 10);
INSERT INTO Menuitem VALUES ('Anklever', 185, 'Förrätt', 'En riktig klassiker', 10);
INSERT INTO Menuitem VALUES ('Grillad entrecôte', 230, 'Huvudrätt', 'Serveras med pommes och grönpepparsås', 10);
INSERT INTO Menuitem VALUES ('Kryddstekt hjortinnanlår', 205, 'Huvudrätt', 'Serveras med pommes och grönpepparsås', 10);
INSERT INTO Menuitem VALUES ('Grönt', 195, 'Huvudrätt', 'Serveras med pommes och grönpepparsås', 10);
INSERT INTO Menuitem VALUES ('Taco', 55, 'Huvudrätt', 'Tortilla,köttfärs,ost', 50);
INSERT INTO Menuitem VALUES ('Mousse', 195, 'Efterrätt', 'Gjord av världens bästa choklad', 10);
INSERT INTO Menuitem VALUES ('Pannacotta', 195, 'Efterrätt', 'Gjord av världens bästa choklad', 10);
INSERT INTO Menuitem VALUES ('Vatten deluxe', 100, 'Dryck', 'Från bergskällan i Roslagen', 10);
INSERT INTO Menuitem VALUES ('Coca cola', 25, 'Dryck', 'Kall', 10);
INSERT INTO Menuitem VALUES ('Ryskt vatten', 80, 'Dryck', 'Kall', 10);

/* INGREDIENTS */
INSERT INTO Ingredient VALUES ('Bröd', 5);
INSERT INTO Ingredient VALUES ('Anka', 10);
INSERT INTO Ingredient VALUES ('Lever', 10);
INSERT INTO Ingredient VALUES ('Grillkål', 5);
INSERT INTO Ingredient VALUES ('Entrecôte', 5);
INSERT INTO Ingredient VALUES ('Hjort', 12);
INSERT INTO Ingredient VALUES ('Löv', 10);
INSERT INTO Ingredient VALUES ('Något', 5);
INSERT INTO Ingredient VALUES ('Socker', 5);
INSERT INTO Ingredient VALUES ('Coca Cola', 10);
INSERT INTO Ingredient VALUES ('Vatten', 10);

/* MENUITEMHASINGREDIENT */
INSERT INTO MenuItemHasIngredient VALUES (1, 'Bröd', 'Toast skagen', 5);
INSERT INTO MenuItemHasIngredient VALUES (2, 'Anka', 'Anklever', 5);
INSERT INTO MenuItemHasIngredient VALUES (3, 'Lever', 'Anklever', 15);
INSERT INTO MenuItemHasIngredient VALUES (4, 'Grillkål', 'Grillad entrecôte', 15);
INSERT INTO MenuItemHasIngredient VALUES (5, 'Entrecôte', 'Grillad entrecôte', 5);
INSERT INTO MenuItemHasIngredient VALUES (6, 'Hjort', 'Kryddstekt hjortinnanlår', 15);
INSERT INTO MenuItemHasIngredient VALUES (7, 'Löv', 'Grönt', 5);
INSERT INTO MenuItemHasIngredient VALUES (8, 'Något', 'Mousse', 15);
INSERT INTO MenuItemHasIngredient VALUES (9, 'Socker', 'Pannacotta', 5);
INSERT INTO MenuItemHasIngredient VALUES (10, 'Vatten', 'Vatten deluxe', 15);
INSERT INTO MenuItemHasIngredient VALUES (11, 'Coca Cola', 'Coca cola', 5);
INSERT INTO MenuItemHasIngredient VALUES (12, 'Vatten', 'Ryskt vatten', 15);

/* EVENT */
INSERT INTO Event VALUES(0, 'U2', '10-12', '2020-12-21', 'event.jpg', 'Inhaler', 'something something');
INSERT INTO Event VALUES(1, 'Green day', '10-12', '2020-12-30', 'event.jpg', 'Hemlig gäst', 'Kom det blir kul');

/* EMPLOYEE */
INSERT INTO Employee VALUES ('Joel');
INSERT INTO Employee VALUES ('Kahre');
INSERT INTO Employee VALUES ('Jocke');

