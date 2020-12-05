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
    FOREIGN KEY (MenuItemName) REFERENCES MenuItem(Name),
    FOREIGN KEY (BillNr) REFERENCES Bill(ID),
    PRIMARY KEY (OrderItemNr, BillNr)
);

CREATE TABLE WeekDay(
    ID SMALLINT NOT NULL PRIMARY KEY,
    Name VARCHAR(8) NOT NULL
);

CREATE TABLE Lunch(
    Name VARCHAR(128) NOT NULL PRIMARY KEY,
    WeekDay SMALLINT,
    FOREIGN KEY (WeekDay) REFERENCES WeekDay(ID)
);

CREATE TABLE Event (
    ID INTEGER NOT NULL PRIMARY KEY,
    Name VARCHAR(20),
    Time TIME,
    Day DATE,
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
INSERT INTO Lunch VALUES ('Kycklinglasagnesoppa', 0);
INSERT INTO Lunch VALUES ('Gröt med mjölk på', 0);
INSERT INTO Lunch VALUES ('Hamburgare med beasås', 0);
INSERT INTO Lunch VALUES ('Kallops med pommes', 1);
INSERT INTO Lunch VALUES ('Pizza med apelsin på', 1);
INSERT INTO Lunch VALUES ('Friterad Lax', 1);
INSERT INTO Lunch VALUES ('Grillad morot', 2);
INSERT INTO Lunch VALUES ('Spaghetti med lök', 2);
INSERT INTO Lunch VALUES ('Gustafskorvmacka', 2);
INSERT INTO Lunch VALUES ('Råbiff', 3);
INSERT INTO Lunch VALUES ('Grillad Sushi', 3);
INSERT INTO Lunch VALUES ('Kalops med fish and chips', 3);
INSERT INTO Lunch VALUES ('Pommeskryddad plankstek', 4);
INSERT INTO Lunch VALUES ('Osaltad Fisksoppa', 4);
INSERT INTO Lunch VALUES ('Kebarulle', 4);