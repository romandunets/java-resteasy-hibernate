INSERT INTO `users` (`email`, `password`) VALUES ("testuser1@example.com", "password");
INSERT INTO `users` (`email`, `password`) VALUES ("testuser2@example.com", "password");
INSERT INTO `users` (`email`, `password`) VALUES ("testuser3@example.com", "password");

INSERT INTO `groups` (`name`, `description`, `user_id`) VALUES ("Java", "Learning Java", 1);
INSERT INTO `groups` (`name`, `description`, `user_id`) VALUES ("JavaScript", "Learning JavaScript", 1);
INSERT INTO `groups` (`name`, `description`, `user_id`) VALUES ("Ruby", "Learning Ruby", 2);

INSERT INTO `notes` (`title`, `content`, `user_id`, `group_id`) VALUES ("Principles", "Write once, run anywhere", 1, 1);
INSERT INTO `notes` (`title`, `content`, `user_id`, `group_id`) VALUES ("Versions", "JDK, J2SE, Java SE, Java EE, Java ME", 1, 1);
INSERT INTO `notes` (`title`, `content`, `user_id`, `group_id`) VALUES ("Definition", "JavaScript is a high-level, dynamic, untyped, and interpreted programming language.", 1, 2);
INSERT INTO `notes` (`title`, `content`, `user_id`, `group_id`) VALUES ("Ruby", "Ruby is dynamic, open source programming language intended for web applications development.", 2, 3);
