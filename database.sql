CREATE SCHEMA IF NOT EXISTS `easy_buy`;

CREATE TABLE IF NOT EXISTS `easy_buy`.`user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL,
    `phone_number` varchar(11) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easy_buy`.`theater` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `city` int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easy_buy`.`movie` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `detail` text DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easy_buy`.`schedule` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `theater_id` int(11) NOT NULL,
    `time` date NOT NULL,
    `hall_number` int(11) NOT NULL,
    `movie_id` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `schedule` (`theater_id`, `time`, `hall_number`),
    KEY `theater_id` (`theater_id`),
    KEY `movie_id` (`movie_id`),
    CONSTRAINT `theater_id` FOREIGN KEY (`theater_id`) REFERENCES `theater` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `movie_id` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easy_buy`.`order` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `date` date NOT NULL,
    `status` tinyint(4) DEFAULT 0,
    `price` int(11) NOT NULL,
    `phone_number` varchar(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easy_buy`.`order_list` (
	`user_id` int(11) NOT NULL,
    `order_id` int(11) NOT NULL,
    PRIMARY KEY (`user_id`, `order_id`),
    KEY `user_id` (`user_id`),
    KEY `order_id` (`order_id`),
	CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `easy_buy`.`ticket` (
	`order_id` int(11) NOT NULL,
    `schedule_id` int(11) NOT NULL,
    `seat_number` int(11) NOT NULL,
	PRIMARY KEY (`schedule_id`, `seat_number`),
    KEY `order_id2` (`order_id`),
    KEY `schedule_id` (`schedule_id`),
    CONSTRAINT `order_id2` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `schedule_id` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
