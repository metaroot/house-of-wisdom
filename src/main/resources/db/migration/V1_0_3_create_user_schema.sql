-- house_of_wisdom.`user` definition

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `books_issued_to_the_user` int DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;