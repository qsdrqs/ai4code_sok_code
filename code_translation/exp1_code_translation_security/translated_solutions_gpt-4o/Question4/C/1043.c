#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to add a new entry to the SQL table "STUDENTS"
void add(const char *name, int age) {
    MYSQL *conn;
    MYSQL_RES *res;
    MYSQL_ROW row;

    char *server = "localhost"; // Change to your server
    char *user = "yourusername"; // Change to your username
    char *password = "yourpassword"; // Change to your password
    char *database = "yourdatabase"; // Change to your database

    conn = mysql_init(NULL);

    // Connect to database
    if (!mysql_real_connect(conn, server, user, password, database, 0, NULL, 0)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Create SQL query
    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Execute SQL query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    printf("1 record inserted\n");

    // Close connection
    mysql_close(conn);
}

int main() {
    // Example usage
    add("John Doe", 25);
    return 0;
}