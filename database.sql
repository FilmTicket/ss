CREATE SCHEMA IF NOT EXISTS `easyBuy`;

CREATE TABLE IF NOT EXISTS `easyBuy`.`user` (
    `userId` INT NOT NULL AUTO_INCREMENT,
    `userName` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `description` TEXT DEFAULT NULL,
    `avatar` TEXT DEFAULT NULL,
    PRIMARY KEY (`userId`),
    UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easyBuy`.`theater` (
	`theaterId` INT NOT NULL AUTO_INCREMENT,
    `theaterName` VARCHAR(50) NOT NULL,
    `theaterAddr` VARCHAR(50) NOT NULL,
    `theaterDis` VARCHAR(50) DEFAULT NULL,
    `theaterLowest` VARCHAR(50) DEFAULT NULL,
    `tag` VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (`theaterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easyBuy`.`movie` (
	`movieId` INT NOT NULL AUTO_INCREMENT,
    `movieName` VARCHAR(50) NOT NULL,
    `movieDes` TEXT DEFAULT NULL,
    `postUrl` TEXT DEFAULT NULL,
    PRIMARY KEY (`movieId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easyBuy`.`popularMovie` (
	`movieId` INT NOT NULL AUTO_INCREMENT,
    `movieName` VARCHAR(50) NOT NULL,
    `movieDes` TEXT DEFAULT NULL,
    `postUrl` TEXT DEFAULT NULL,
    PRIMARY KEY (`movieId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easyBuy`.`movieTime` (
	`movieTimeId` INT NOT NULL AUTO_INCREMENT,
    `date` VARCHAR(50) NOT NULL,
    `startTime` VARCHAR(50) NOT NULL,
    `endTime` VARCHAR(50) NOT NULL,
    `movieId` INT NOT NULL,
    `movieType` VARCHAR(50) NOT NULL,
    `theaterId` INT NOT NULL,
    `price` VARCHAR(50) NOT NULL,
    `hallName` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`movieTimeId`),
    UNIQUE KEY `schedule` (`theaterId`, `date`, `startTime`, `hallName`),
    KEY `movieId` (`movieId`),
    KEY `theaterId` (`theaterId`),
    CONSTRAINT `movieId` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `theaterId` FOREIGN KEY (`theaterId`) REFERENCES `theater` (`theaterId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easyBuy`.`seatInfo` (
	`seatId` INT NOT NULL AUTO_INCREMENT,
    `position` INT NOT NULL,
    `row` INT NOT NULL,
    `column` INT NOT NULL,
    `status` TINYINT(1) NOT NULL,
    `movieTimeId` INT NOT NULL,
    PRIMARY KEY (`seatId`),
    KEY `movieTimeId` (`movieTimeId`),
    CONSTRAINT `movieTimeId` FOREIGN KEY (`movieTimeId`) REFERENCES `movieTime` (`movieTimeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easyBuy`.`ticket` (
	`ticketId` INT NOT NULL AUTO_INCREMENT,
	`userId` INT NOT NULL,
    `movieName` VARCHAR(50) NOT NULL,
    `theaterName` VARCHAR(50) NOT NULL,
    `dateTime` VARCHAR(50) NOT NULL,
    `hallName` VARCHAR(50) NOT NULL,
    `seats` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`ticketId`),
    KEY `userId` (`userId`),
    CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
