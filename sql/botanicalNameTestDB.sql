
DROP TABLE if EXISTS PlantList, userToPlantList, PlantKorName, Plants, user;

CREATE TABLE user (
	userId VARCHAR(20) NOT NULL PRIMARY KEY,
	userPassword VARCHAR(20) NOT NULL
);

CREATE TABLE userToPlantList (
	listId INTEGER AUTO_INCREMENT PRIMARY KEY,
	userId VARCHAR(20) NOT NULL,
	FOREIGN KEY(userId) REFERENCES user(userId)
	    on update cascade
        on delete cascade
);

CREATE TABLE plants (
	plantId INTEGER PRIMARY KEY,
    plantKorName VARCHAR(100) NOT NULL,
	familyName VARCHAR(100) NOT NULL,
    familyKorName VARCHAR(100) NOT NULL,
	genusName VARCHAR(100) NOT NULL,
    genusKorName VARCHAR(100) NOT NULL,
	botanicalName VARCHAR(100) NOT NULL,
	imgUrl VARCHAR(2083)
);

CREATE TABLE plantList (
	listId INTEGER NOT NULL,
	plantId INTEGER NOT NULL,
	FOREIGN KEY(listId) REFERENCES userToPlantList(listId)
        on update cascade
        on delete cascade,
	FOREIGN KEY(plantId) REFERENCES plants(plantId)
        on update cascade
        on delete cascade,
	PRIMARY KEY (listId, plantId)
);

ALTER table plants convert to character set utf8;

CREATE OR REPLACE INDEX IDX_PLANTS ON plants (plantKorName);

