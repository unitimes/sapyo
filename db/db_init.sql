DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`email` VARCHAR(32) NOT NULL,
	`password` VARCHAR(64) NOT NULL,
	`familyname` VARCHAR(64) NOT NULL,
	`class` VARCHAR(64) NOT NULL,
	`company` VARCHAR(255) NOT NULL,
	PRIMARY KEY(`email`)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


DROP TABLE IF EXISTS `sapyo`;
CREATE TABLE `sapyo` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(32) NOT NULL,
	`submittime` DATETIME NOT NULL,
	PRIMARY KEY(`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


DROP TABLE IF EXISTS `reason`;
CREATE TABLE `reason` (
	`id` INTEGER NOT NULL,
	`reason` TEXT NOT NULL,
	PRIMARY KEY(`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS `reasonagreement`;
CREATE TABLE `reasonagreement` (
	`id` INTEGER NOT NULL,
	`email` VARCHAR(32) NOT NULL,
	`type` TINYINT NOT NULL,
	PRIMARY KEY(`id`, `email`)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;
