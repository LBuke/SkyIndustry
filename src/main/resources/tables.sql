
-- Base user information
CREATE TABLE IF NOT EXISTS `user` (
    `uuid` BINARY(16) NOT NULL,
    `name` VARCHAR(16) NOT NULL,
    `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`uuid`)
);

CREATE TABLE IF NOT EXISTS `achievement` (
    `uuid` BINARY(16) NOT NULL,
    `id` INT NOT NULL,
    `data` INT NOT NULL DEFAULT 0,
    `complete` BOOLEAN NOT NULL DEFAULT FALSE,
    `date_complete` TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (`uuid`, `id`),
    FOREIGN KEY (`uuid`) REFERENCES `user`(`uuid`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `server` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL,
    `capacity` INT NOT NULL DEFAULT 50,
    PRIMARY KEY (`id`)
);