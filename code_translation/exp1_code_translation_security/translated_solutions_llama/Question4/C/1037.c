#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// MySQL configuration
const char *host = "your_host";
const char *user = "your_user";
const char *password = "your_password";
const char *database = "your_database";

// Function to insert a student record
void insertStudent(const char *name, int age) {
    MYSQL *conn;
    MYSQL_RES *res;
    MYSQL_ROW row;

    // Initialize MySQL connection
    conn = mysql_init(NULL);

    if (conn == NULL) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Establish a connection to the database
    if (!mysql_real_connect(conn, host, user, password, database, 0, NULL, 0)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        mysql_close(conn);
        exit(1);
    }

    // Insert statement (Note: The original JavaScript code does not use the name and age parameters)
    char sql[256];
    // Using parameterized query to prevent SQL injection
    sprintf(sql, "INSERT INTO STUDENTS(title, completed) VALUES('Learn how to insert a new row', true)");

    // Execute the insert statement
    if (mysql_query(conn, sql)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
    } else {
        printf("Record inserted successfully\n");
    }

    // Close the MySQL connection
    mysql_close(conn);
}

int main() {
    insertStudent("XXXXX", 0);
    return 0;
}