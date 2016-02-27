INSERT INTO `users` (`email`, `password`) VALUES ("testuser1@example.com", "password");
INSERT INTO `users` (`email`, `password`) VALUES ("testuser2@example.com", "password");
INSERT INTO `users` (`email`, `password`) VALUES ("testuser3@example.com", "password");

INSERT INTO `notes` (`title`, `content`, `user_id`) VALUES ("Java", "write once, run anywhere", 1);
INSERT INTO `notes` (`title`, `content`, `user_id`) VALUES ("JavaScript", "client-side programming language", 1);
INSERT INTO `notes` (`title`, `content`, `user_id`) VALUES ("Ruby", "dynamic, open source programming language", 2);

INSERT INTO `groupes` (`name`, `description`, `user_id`) VALUES ("Java", "Learning Java", 1);
INSERT INTO `groupes` (`name`, `description`, `user_id`) VALUES ("JavaScript", "Learning JavaScript", 1);
INSERT INTO `groupes` (`name`, `description`, `user_id`) VALUES ("Ruby", "Learning Ruby", 2);
