DROP TABLE Credits;
DROP TABLE Logs;
DROP TABLE Compte;
DROP TABLE Client;

CREATE TABLE Client
(
	login VARCHAR(50) CONSTRAINT PK_Client PRIMARY KEY,
	nom VARCHAR(50),
	prenom VARCHAR(50)
);

CREATE TABLE Compte
(
	idCompte VARCHAR(20) CONSTRAINT PK_Compte PRIMARY KEY,
	solde FLOAT,
	refClient VARCHAR(50) CONSTRAINT FK_Compte_refClient REFERENCES Client(login)
);

CREATE TABLE Logs
(
	idLogs VARCHAR(5) CONSTRAINT PK_Logs PRIMARY KEY,
	infos VARCHAR(500),
	dateHeure TIMESTAMP
);

CREATE TABLE Credits
(
	idCredit VARCHAR(10) CONSTRAINT PK_Credits PRIMARY KEY,
	montant FLOAT,
	taux FLOAT,
	duree INTEGER,
	salaire FLOAT,
	chargeCredit FLOAT,
	accorde BOOLEAN,
	refClient VARCHAR(50) CONSTRAINT FK_Credits_refClient REFERENCES Client(login)
);

INSERT INTO Client VALUES('adrien','Desart','Adrien');
INSERT INTO Client VALUES('cedric','Degeneffe','Cedric');
INSERT INTO Compte VALUES('0',200,'adrien');
INSERT INTO Compte VALUES('1',50,'adrien');
INSERT INTO Compte VALUES('2',1000,'adrien');
INSERT INTO Compte VALUES('3',450,'adrien');
INSERT INTO Compte VALUES('4',750,'cedric');
INSERT INTO Compte VALUES('5',1200,'cedric');
INSERT INTO Compte VALUES('6',400,'cedric');
