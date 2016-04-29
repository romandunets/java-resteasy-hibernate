INSERT INTO `users` (`email`, `password`) VALUES ("testuser1@example.com", "password");
INSERT INTO `users` (`email`, `password`) VALUES ("testuser2@example.com", "password");
INSERT INTO `users` (`email`, `password`) VALUES ("testuser3@example.com", "password");

INSERT INTO `groups` (`name`, `description`, `user_id`) VALUES ("Java", "Java Tutorial for Beginners.", 1);
INSERT INTO `groups` (`name`, `description`, `user_id`, `parent_group_id`) VALUES ("Overview", "Getting started with Java.", 1, 1);
INSERT INTO `groups` (`name`, `description`, `user_id`, `parent_group_id`) VALUES ("Syntax", "Java programming language syntax.", 1, 1);
INSERT INTO `groups` (`name`, `description`, `user_id`) VALUES ("JavaScript", "Learning JavaScript", 1);
INSERT INTO `groups` (`name`, `description`, `user_id`) VALUES ("Ruby", "Learning Ruby", 2);

INSERT INTO `notes` (`title`, `content`, `user_id`, `group_id`) VALUES ("Principles", "Main principle of Java is: 'Write once, run anywhere'", 1, 2);
INSERT INTO `notes` (`title`, `content`, `user_id`, `group_id`) VALUES ("Java Versions", "Java has the following versions: JDK, J2SE, Java SE, Java EE, Java ME.", 1, 2);
INSERT INTO `notes` (`title`, `content`, `user_id`, `group_id`) VALUES ("Objects", "Objects have states and behaviours.", 1, 3);
INSERT INTO `notes` (`title`, `content`, `user_id`, `group_id`) VALUES ("Definition", "JavaScript is a high-level, dynamic, untyped, and interpreted programming language.", 1, 4);
INSERT INTO `notes` (`title`, `content`, `user_id`, `group_id`) VALUES ("Ruby", "Ruby is dynamic, open source programming language intended for web applications development.", 2, 5);

INSERT INTO `connection_types` (`name`, `description`) VALUES ("child", "Child to parent connection type");
