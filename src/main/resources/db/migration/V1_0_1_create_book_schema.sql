-- house_of_wisdom.book definition

CREATE TABLE `book` (
  `id` bigint NOT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `book_meta_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg54hkbxvec8ifqutqp4746ohp` (`book_meta_id`),
  CONSTRAINT `FKg54hkbxvec8ifqutqp4746ohp` FOREIGN KEY (`book_meta_id`) REFERENCES `book_meta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;