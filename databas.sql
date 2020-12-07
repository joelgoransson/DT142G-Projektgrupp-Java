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

CREATE TABLE Tables(
    TableNr INTEGER NOT NULL PRIMARY KEY
);

CREATE TABLE Employee(
    ID INTEGER NOT NULL PRIMARY KEY,
    Name VARCHAR(55),
    Password VARCHAR(55)
);

CREATE TABLE Bill (
    ID INTEGER NOT NULL PRIMARY KEY,
    Date DATE,
    Status VARCHAR(40),
    TableNr INTEGER,
    EmployeeID INTEGER,
    Time TIME,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(ID),
    FOREIGN KEY (TableNr) REFERENCES Tables(TableNr)
);

CREATE TABLE MenuItem (
    Name VARCHAR(128) NOT NULL PRIMARY KEY,
    Price INTEGER,
    Type VARCHAR(55),
    PrepTime DOUBLE
);

CREATE TABLE OrderMenuItem(
    Quantity INTEGER NOT NULL,
    Comment VARCHAR(55),
    OrderItemNr INTEGER NOT NULL,
    MenuItemName VARCHAR(128),
    BillNr INTEGER,
    FOREIGN KEY (MenuItemName) REFERENCES MenuItem(Name) ON DELETE CASCADE,
    FOREIGN KEY (BillNr) REFERENCES Bill(ID),
    PRIMARY KEY (OrderItemNr, BillNr)
);

CREATE TABLE WeekDay(
    ID SMALLINT NOT NULL PRIMARY KEY,
    Name VARCHAR(8) NOT NULL
);

CREATE TABLE Lunch(
    ID INTEGER NOT NULL PRIMARY KEY,
    Name VARCHAR(128),
    WeekDay SMALLINT,
    FOREIGN KEY (WeekDay) REFERENCES WeekDay(ID)
);

CREATE TABLE Event (
    ID INTEGER NOT NULL PRIMARY KEY,
    Name VARCHAR(20),
    Clock VARCHAR(50),
    Day VARCHAR(50),
    Image VARCHAR(500), 
    OpeningAct VARCHAR(20),
    Description VARCHAR(50)
);

CREATE TABLE Pass (
    ID INTEGER NOT NULL PRIMARY KEY,
    StartTime TIME,
    StopTime TIME,
    Date DATE,
    EmployeeID INTEGER,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(ID)
);

CREATE TABLE Ingredient (
    Name VARCHAR(55) NOT NULL PRIMARY KEY,
    Amount INTEGER
);

CREATE TABLE MenuItemHasIngredient (
    IngredientName VARCHAR(55),
    MenuItemName VARCHAR(128),
    Quantity DOUBLE,
    FOREIGN KEY (MenuItemName) REFERENCES MenuItem(Name),
    FOREIGN KEY (IngredientName) REFERENCES Ingredient(Name),
    PRIMARY KEY (MenuItemName, IngredientName)
);

/* INSERTS */

/* Weekday */
INSERT INTO WeekDay VALUES (0, 'Måndag');
INSERT INTO WeekDay VALUES (1, 'Tisdag');
INSERT INTO WeekDay VALUES (2, 'Onsdag');
INSERT INTO WeekDay VALUES (3, 'Torsdag');
INSERT INTO WeekDay VALUES (4, 'Fredag');

/* Day */ 
INSERT INTO Lunch VALUES (0, 'Kycklinglasagnesoppa', 0);
INSERT INTO Lunch VALUES (1, 'Gröt med mjölk på', 0);
INSERT INTO Lunch VALUES (2, 'Hamburgare med beasås', 0);
INSERT INTO Lunch VALUES (3, 'Kallops med pommes', 1);
INSERT INTO Lunch VALUES (4, 'Pizza med apelsin på', 1);
INSERT INTO Lunch VALUES (5, 'Friterad Lax', 1);
INSERT INTO Lunch VALUES (6, 'Grillad morot', 2);
INSERT INTO Lunch VALUES (7, 'Spaghetti med lök', 2);
INSERT INTO Lunch VALUES (8, 'Gustafskorvmacka', 2);
INSERT INTO Lunch VALUES (9, 'Råbiff', 3);
INSERT INTO Lunch VALUES (10, 'Grillad Sushi', 3);
INSERT INTO Lunch VALUES (11, 'Kalops med fish and chips', 3);
INSERT INTO Lunch VALUES (12, 'Pommeskryddad plankstek', 4);
INSERT INTO Lunch VALUES (13, 'Osaltad Fisksoppa', 4);
INSERT INTO Lunch VALUES (14, 'Kebarulle', 4);

/* EVENT */
INSERT INTO Event VALUES(0, 'U2', '10-12', '21/7 - 23/7', 'test', 'Inhaler', 'something something');