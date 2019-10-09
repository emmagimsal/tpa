CREATE DATABASE /*!32312 IF NOT EXISTS*/`tutorial_db`;

USE `tutorial_db`;

CREATE USER 'tutorial_usr'@'LOCALHOST' IDENTIFIED BY 'tpa2019';

GRANT ALL PRIVILEGES ON `tutorial_db`.* TO 'tutorial_usr'@'LOCALHOST' WITH GRANT OPTION;

flush PRIVILEGES;