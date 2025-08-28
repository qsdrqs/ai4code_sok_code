#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// Function to create a new entry in the STUDENTS table
void createEntry(MYSQL *con, const char *name, int age) {
    // Prepare the query
    char query[256];
    sprintf(query, "INSERT INTO STUDENTS (name, age) VALUES ('%s', %d)", name, age);

    // Execute the query
    if (mysql_query(con, query)) {
        fprintf(stderr, "Error: %s\n", mysql_error(con));
        exit(1);
    }
}

int main() {
    // Initialize MySQL library
    mysql_library_init(0, NULL, NULL);

    // Create a new MySQL connection
    MYSQL *con = mysql_init(NULL);
    if (!con) {
        fprintf(stderr, "mysql_init() failed\n");
        mysql_library_end();
        exit(1);
    }

    // Connect to the database
    if (!mysql_real_connect(con, "your_host", "your_user", "your_password", "your_database", 0, NULL, 0)) {
        fprintf(stderr, "mysql_real_connect() failed: %s\n", mysql_error(con));
        mysql_close(con);
        mysql_library_end();
        exit(1);
    }

    // Create a new entry
    createEntry(con, "John Doe", 25);

    // Close the connection and clean up
    mysql_close(con);
    mysql_library_end();

    return 0;
}