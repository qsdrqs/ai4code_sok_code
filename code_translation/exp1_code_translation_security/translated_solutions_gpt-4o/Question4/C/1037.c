#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h> // MySQL client library

void insertStudent(const char *name, int age) {
    // MySQL connection variables
    MYSQL *conn;
    MYSQL_RES *res;
    MYSQL_ROW row;

    // MySQL connection configuration
    const char *server = "localhost";  // Replace with your MySQL server address
    const char *user = "your_username"; // Replace with your MySQL username
    const char *password = "your_password"; // Replace with your MySQL password
    const char *database = "your_database"; // Replace with your database name

    // Initialize MySQL connection
    conn = mysql_init(NULL);
    if (conn == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    // Connect to the database
    if (mysql_real_connect(conn, server, user, password, database, 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(conn);
        exit(EXIT_FAILURE);
    }

    // SQL insert statement
    const char *sql = "INSERT INTO STUDENTS(title, completed) VALUES('Learn how to insert a new row', true)";

    // Execute the SQL query
    if (mysql_query(conn, sql)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(conn));
        mysql_close(conn);
        exit(EXIT_FAILURE);
    }

    // Close the connection
    mysql_close(conn);
}

int main() {
    // Call the function to insert a student
    insertStudent("XXXXX", 0);

    return 0;
}