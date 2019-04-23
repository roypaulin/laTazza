/* table Transitions definitions */
CREATE TABLE `Transactions` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`employeeID`	INTEGER NOT NULL,
	`amount`	REAL NOT NULL,
	`isEmployee`	NUMERIC NOT NULL CHECK(isEmployee=0 OR isEmployee=1),
	`startDate`	TEXT NOT NULL CHECK(date(startDate) IS NOT NULL),
	`endDate`	TEXT NOT NULL CHECK(date(endDate) IS NOT NULL)
);
/* table Employee definitions */
CREATE TABLE `Employee` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT NOT NULL,
	`surname`	TEXT NOT NULL,
	`credit`	REAL NOT NULL DEFAULT 0 CHECK(credit >= 0)
);
/* table Beverage definitions */
CREATE TABLE `Beverage` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`quantityAvaiable`	INTEGER NOT NULL DEFAULT 0 CHECK(quantityAvaiable>=0),
	`price`	REAL NOT NULL CHECK(price>0),
	`capsulePerBox`	INTEGER NOT NULL CHECK(capsulePerBox > 0),
	`name`	TEXT NOT NULL UNIQUE
);
/* table LaTazza definitions */
CREATE TABLE `LaTazza` (
	`balance`	REAL NOT NULL DEFAULT 0 CHECK(balance>=0)
);
