#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to add a student to the database
void addStudent(MYSQL *connection, const char *name, int age) {
    // Prepare the SQL query
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (name, age) VALUES ('%s', %d)", name, age);

    // Execute the SQL query
    if (mysql_query(connection, sql)) {
        fprintf(stderr, "Error inserting record: %s\n", mysql_error(connection));
        exit(1);
    }

    // Check the number of affected rows
    if (mysql_affected_rows(connection) == 1) {
        printf("1 record inserted\n");
    } else {
        fprintf(stderr, "Error inserting record\n");
    }
}

int main() {
    // Initialize MySQL library
    mysql_library_init(0, NULL, NULL);

    // Create a MySQL connection object
    MYSQL *connection = mysql_init(NULL);

    // Establish a connection to the database
    if (!mysql_real_connect(connection, "your_host", "your_username", "your_password", "your_database", 0, NULL, 0)) {
        fprintf(stderr, "Error connecting to database: %s\n", mysql_error(connection));
        mysql_close(connection);
        mysql_library_end();
        exit(1);
    }

    // Add a student to the database
    addStudent(connection, "John Doe", 20);

    // Close the database connection
    mysql_close(connection);

    // Clean up MySQL library
    mysql_library_end();

    return 0;
}