#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to add a new entry to the "STUDENTS" table
void add(MYSQL *connection, const char *name, int age) {
    char sql[256];
    // Create SQL query string
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    printf("%s\n", sql);

    // Execute SQL query
    if (mysql_query(connection, sql)) {
        fprintf(stderr, "Error: %s\n", mysql_error(connection));
        exit(1);
    }

    printf("1 record inserted\n");
}

int main() {
    MYSQL *connection;
    MYSQL_RES *result;
    MYSQL_ROW row;

    // Initialize MySQL library
    mysql_library_init(0, NULL, NULL);

    // Create a new MySQL connection
    connection = mysql_init(NULL);

    if (!mysql_real_connect(connection, "your_host", "your_username", "your_password", "your_database", 0, NULL, 0)) {
        fprintf(stderr, "Error: %s\n", mysql_error(connection));
        mysql_close(connection);
        mysql_library_end();
        exit(1);
    }

    // Example usage
    add(connection, "John Doe", 20);

    // Close MySQL connection
    mysql_close(connection);

    // Clean up MySQL library
    mysql_library_end();

    return 0;
}