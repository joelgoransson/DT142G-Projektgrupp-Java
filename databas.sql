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
    Day VARCHAR(50),
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
    EmployeeID INTEGER,
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
INSERT INTO Menuitem VALUES ('Toast skagen', 195, 'förrätt', 10);
INSERT INTO Menuitem VALUES ('Anklever', 185, 'förrätt', 10);
INSERT INTO Menuitem VALUES ('Grillad antrecôte', 230, 'huvudrätt', 10);
INSERT INTO Menuitem VALUES ('Kryddstekt hjortinnanlår', 205, 'huvudrätt', 10);
INSERT INTO Menuitem VALUES ('Grönt', 195, 'huvudrätt', 10);
INSERT INTO Menuitem VALUES ('Mousse', 195, 'efterrätt', 10);
INSERT INTO Menuitem VALUES ('Pannacotta', 195, 'efterrätt', 10);
INSERT INTO Menuitem VALUES ('Vatten deluxe', 100, 'dryck', 10);
INSERT INTO Menuitem VALUES ('Coca cola', 25, 'dryck', 10);
INSERT INTO Menuitem VALUES ('Ryskt vatten', 80, 'dryck', 10);

/* INGREDIENTS */
INSERT INTO Ingredient VALUES ('Bröd', 5);
INSERT INTO Ingredient VALUES ('Anka', 10);
INSERT INTO Ingredient VALUES ('Lever', 10);
INSERT INTO Ingredient VALUES ('Grillkål', 5);
INSERT INTO Ingredient VALUES ('Antrecôte', 5);
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
INSERT INTO MenuItemHasIngredient VALUES (4, 'Grillkål', 'Grillad antrecôte', 15);
INSERT INTO MenuItemHasIngredient VALUES (5, 'Antrecôte', 'Grillad antrecôte', 5);
INSERT INTO MenuItemHasIngredient VALUES (6, 'Hjort', 'Kryddstekt hjortinnanlår', 15);
INSERT INTO MenuItemHasIngredient VALUES (7, 'Löv', 'Grönt', 5);
INSERT INTO MenuItemHasIngredient VALUES (8, 'Något', 'Mousse', 15);
INSERT INTO MenuItemHasIngredient VALUES (9, 'Socker', 'Pannacotta', 5);
INSERT INTO MenuItemHasIngredient VALUES (10, 'Vatten', 'Vatten deluxe', 15);
INSERT INTO MenuItemHasIngredient VALUES (11, 'Coca Cola', 'Coca cola', 5);
INSERT INTO MenuItemHasIngredient VALUES (12, 'Vatten', 'Ryskt vatten', 15);

/* EVENT */
INSERT INTO Event VALUES(0, 'U2', '10-12', '21/7 - 23/7', 'test', 'Inhaler', 'something something');

/* PASS */
INSERT INTO Pass VALUES (0, 1, 1, 1);
INSERT INTO Pass VALUES (1, 2, 1, 1);
INSERT INTO Pass VALUES (2, 1, 2, 1);
INSERT INTO Pass VALUES (3, 2, 2, 1);
INSERT INTO Pass VALUES (4, 1, 3, 1);
INSERT INTO Pass VALUES (5, 2, 3, 1);
INSERT INTO Pass VALUES (6, 1, 4, 1);
INSERT INTO Pass VALUES (7, 2, 4, 1);
INSERT INTO Pass VALUES (8, 1, 5, 1);
INSERT INTO Pass VALUES (9, 2, 5, 1);
INSERT INTO Pass VALUES (10, 1, 6, 1);
INSERT INTO Pass VALUES (11, 2, 6, 1);

INSERT INTO Pass VALUES (12, 1, 1, 2);
INSERT INTO Pass VALUES (13, 2, 1, 2);
INSERT INTO Pass VALUES (14, 1, 2, 2);
INSERT INTO Pass VALUES (15, 2, 2, 2);
INSERT INTO Pass VALUES (16, 1, 3, 2);
INSERT INTO Pass VALUES (17, 2, 3, 2);
INSERT INTO Pass VALUES (18, 1, 4, 2);
INSERT INTO Pass VALUES (19, 2, 4, 2);
INSERT INTO Pass VALUES (20, 1, 5, 2);
INSERT INTO Pass VALUES (21, 2, 5, 2);
INSERT INTO Pass VALUES (22, 1, 6, 2);
INSERT INTO Pass VALUES (23, 2, 6, 2);

/* EMPLOYEE */
INSERT INTO Employee VALUES (0, 'Joel', 'Hej123');
INSERT INTO Employee VALUES (1, 'Kahre', 'Wow1337');
INSERT INTO Employee VALUES (2, 'Jocke', 'Wowjocke');

/* EMPLOYEEPASS */
INSERT INTO EmployeePass VALUES (1, 0, 0);
INSERT INTO EmployeePass VALUES (2, 1, 0);
INSERT INTO EmployeePass VALUES (3, 2, 0);
INSERT INTO EmployeePass VALUES (4, 0, 1);
INSERT INTO EmployeePass VALUES (5, 1, 1);
INSERT INTO EmployeePass VALUES (6, 2, 1);
INSERT INTO EmployeePass VALUES (7, 0, 2);
INSERT INTO EmployeePass VALUES (8, 1, 2);
INSERT INTO EmployeePass VALUES (9, 2, 2);
INSERT INTO EmployeePass VALUES (10, 0, 3);
INSERT INTO EmployeePass VALUES (11, 1, 3);
INSERT INTO EmployeePass VALUES (12, 2, 3);
INSERT INTO EmployeePass VALUES (13, 0, 4);
INSERT INTO EmployeePass VALUES (14, 1, 4);
INSERT INTO EmployeePass VALUES (15, 2, 4);
INSERT INTO EmployeePass VALUES (16, 0, 5);
INSERT INTO EmployeePass VALUES (17, 1, 5);
INSERT INTO EmployeePass VALUES (18, 2, 5);
INSERT INTO EmployeePass VALUES (19, 0, 6);
INSERT INTO EmployeePass VALUES (20, 1, 6);
INSERT INTO EmployeePass VALUES (21, 2, 6);
INSERT INTO EmployeePass VALUES (22, 0, 7);
INSERT INTO EmployeePass VALUES (23, 1, 7);
INSERT INTO EmployeePass VALUES (24, 2, 7);
INSERT INTO EmployeePass VALUES (25, 0, 8);
INSERT INTO EmployeePass VALUES (26, 1, 8);
INSERT INTO EmployeePass VALUES (27, 2, 8);
INSERT INTO EmployeePass VALUES (28, 0, 9);
INSERT INTO EmployeePass VALUES (29, 1, 9);
INSERT INTO EmployeePass VALUES (30, 2, 9);
INSERT INTO EmployeePass VALUES (31, 0, 10);
INSERT INTO EmployeePass VALUES (32, 1, 10);
INSERT INTO EmployeePass VALUES (33, 2, 10);
INSERT INTO EmployeePass VALUES (34, 0, 11);
INSERT INTO EmployeePass VALUES (35, 1, 11);
INSERT INTO EmployeePass VALUES (36, 2, 11);
