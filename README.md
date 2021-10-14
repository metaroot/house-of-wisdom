# Project House-of-wisdom is a Rest API Server for Library management.

# Technologies used:
1) Spring boot
2) MySQL
3) Docker

**Reason for using spring boot:** Spring makes programming Java quicker, easier, and safer for everybody. Spring’s focus on speed, simplicity, and productivity has made it the World's most popular Java framework. This is considered to be one of the most robust and reliable frameworks for Backend Engineering.

**Reason for using MySQL:** As our data is structured and unchanging, data security is crucial and ACID properties will be really helpful here so I have used MySQL.


# Steps to Run the application:

1) Git clone: https://github.com/metaroot/house-of-wisdom.git
2) Navigate to house-of-wisdom directory
3) Run: mvn clean install
4) Run: mvn spring-boot:run

The application can be run using the docker image also.


There are 2 primary entities and 2 supporting entities. Each of their functionalities is described below:


# Entities used:
# 1) user: 
This table consists of id, user_name, books_issued_to_the_user.

A user table looks like this:
|id|books_issued_to_the_user|user_name                      |
|--|------------------------|-------------------------------|
| 9|                       5|Mamun                          |
|10|                       2|Imam                           |
|11|                       3|Imam Gazzali Ra                |
|64|                       3|Amir Ul Muminin Harun Ur Rashid|



# 2) book:

This table consists of id, name, author_name, book_meta_id.
A book table looks like this:


id|author_name          |name                                          |book_meta_id|
--|---------------------|----------------------------------------------|------------|
56|Imam Ibn Taymiyyah Ra|The Removal of Blame from the Great Imams     |          73|
58|imam ghazali         | bidayatul hidayah                            |          59|


# 3) book_meta: 

This table consists of id, number_of_available_copies, number_of_pages, published_date, publisher_name
A book_meta table looks like this:


id|number_of_available_copies|number_of_pages|published_date|publisher_name|
--|--------------------------|---------------|--------------|--------------|
73|                        24|           1109|    1007-01-13|bait-al-hikmah|
59|                        25|           1310|    1197-02-12|bait-al-hikmah|


# 4) borrow: 

This table consists of id, book_id, combination_occurences, user_id
A borrow Table looks like this:




id|book_id|combination_occurences|user_id|
--|-------|----------------------|-------|
28|     56|                     1|      9|
30|     58|                     2|     10|



# The following table represents the API Documentation of the project

Method Type|API Endpoint                         |Request Body                         |Request Param      |Path Variable        |short description                         |
-----------|-------------------------------------|-------------------------------------|-------------------|---------------------|------------------------------------------|
POST       |/users/create                        | Json in form of an User Entity      |     N/A           |     N/A             |Creates an user                           |
POST       |/books/create                        | Json in form of a Book Entity       |     N/A           |     N/A             |Creates a Book                            |
GET        | /users/get/{id}                     |           N/A                       |     N/A           | id of type Long     |Fetch an User                             |
GET        | /books/get/{id}                     |           N/A                       |     N/A           | id of type Long     |Fetch a  Book                             |
GET        | /users/all                          |           N/A                       |     N/A           |     N/A             |Gets all User                             |
GET        | /books/all                          |           N/A                       |     N/A           |     N/A             |Gets all Book                             |
PUT        | /users/update/{id}                  |           N/A                       |     N/A           | id of type Long     |Updates the user by id                    |
PUT        | /books/update/{id}                  |           N/A                       |     N/A           | id of type Long     |Updates the book by id                    |
DELETE     | /users/delete/{id}                  |           N/A                       |     N/A           | id of type Long     |Delete the user by id                     |
DELETE     | /books/delete/{id}                  |           N/A                       |     N/A           | id of type Long     |Delete the book by id                     |
GET        | /books/search-by-book-name/{name}   |           N/A                       |     N/A           | name of type string |Search by book name                       |
GET        | /books/search-by-author-name/{name} |           N/A                       |     N/A           | name of type string |Search by author name                     |
POST       |  /borrow/take                       |           N/A                       |Long userId, bookId|      N/A            |User with userId takes book with bookId   |
POST       |  /borrow/return                     |           N/A                       |Long userId, bookId|      N/A            |User with userId returns book with bookId |
GET        | /borrow/list-of-users-by-book/{id}  |           N/A                       |     N/A           | id of type long     |List of users by bookId = id              |
GET        | /borrow/list-of-books-by-user/{id}  |           N/A                       |     N/A           | id of type long     |List of books by userId = id              |

# Higher level solution to the book borrow and returning problem:

When the /borrow/take  endpoint is hit with the userId and bookId, and user with userId hadn't taken more than 5 books and book with book id is in stock then following happen:

1) if this combination didn't occur before then we entry in the borrow table this combination and the combination_occurences is set to 1
and if this combination occured before then combination_occurences becomes combination_occurences + 1

2) books_taken_by_user in the user table becomes  books_taken_by_user + 1

3) number_of_available_copies in the book meta table becomes number_of_available_copies - 1

When the /borrow/return  endpoint is hit with the userId and bookId, then the following happen:

1) if this combination occurred only once then we remove the entry and if this combination occurred multiple times then combination_occurences becomes combination_occurences - 1

2) books_taken_by_user in the user table becomes  books_taken_by_user - 1

3) number_of_available_copies in the book meta table becomes number_of_available_copies + 1

Thus using this reflexive algorithm the borrow and return is maintained in the system.


# Enginerring approaches to follow all the given instructions:

# 1) Localization
Localization with support for English(default) and Japanese:
This was implemented using WebMvcConfigurer. Spring boot'ss LocalResolver @Bean had been used.
The basic class which implements WebMvcConfigurer can be found at src/main/java/com/houseofwisdom/houseofwisdom/configs/Localization.java

# 2) Book Relation and Fetching
Book Meta and Book having a 1 - 1 relationship had been ensured in the Model level. To Fetch only the Book data without relationship while GETing a Book I have used a BookDTO class where I coded the desired field and used the DTO class to fetch Book data.

For DTO to model conversion in the controller layer, I used ModelMapper by @AutoWired


# 3) Request Id
To implement every request must containing a request-id and this value must be prefixed in logs throughout different layers of the application I wrote a Slf4jMDCFilter class that extends OncePerRequestFilter and I have Put a randomly generated Unique token in the MDC of slf4j.

Using this class I have implented a @Configuration and to ensure that request_id as prefix when logging every request I used logging.pattern.console in the application.properties.

# 4) API req/resp and DB write format
To implement API requests/responses sending dates as DD-MM-YYYY I have used @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") in the entity layer. 


and to save as YYYY-MM-DD format in the database I have used SQL DDL definition.


# 5) snake_case and camelCase
To  implement Request/Response body being in snake_case and in Java side using camelCase I have used spring.jackson.property-naming-strategy in the application properties.


# 6) Tomcat Config
I have configured Tomcat to have 300 threads at max, by using server.tomcat.threads.max

# 7) DB migration on app startUp
I have used flyway to create db on app startup to do so I needed to put the DDL under db/migration, I have written a FlyWayConfig in the project root also enabling and setting up the spring and flyway connection in the application properties.

# 8) Exception Handling
To handle common exceptions(400, 204) cases using global configuration I used @ControllerAdvice. Handled the common exception by writting classes in the src/main/java/com/houseofwisdom/houseofwisdom/exceptions package.


# 9) Dockerize
Dockerized and published the docker image in the docker hub repo.

# 10) CI With Github actions
1Wrote workflows/main.yml to implement the CI using github actions.
