-- house_of_wisdom.book_meta definition

CREATE TABLE `book_meta` (
  `id` bigint NOT NULL,
  `number_of_available_copies` int DEFAULT NULL,
  `number_of_pages` int DEFAULT NULL,
  `published_date` date DEFAULT NULL,
  `publisher_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;