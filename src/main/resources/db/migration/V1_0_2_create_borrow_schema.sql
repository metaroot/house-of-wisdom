-- house_of_wisdom.borrow definition

CREATE TABLE `borrow` (
  `id` bigint NOT NULL,
  `book_id` bigint DEFAULT NULL,
  `combination_occurences` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;