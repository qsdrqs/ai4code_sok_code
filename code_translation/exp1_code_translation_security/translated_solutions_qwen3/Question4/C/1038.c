#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to insert a student into the STUDENTS table
void insertion(const char* name, int age) {
    MYSQL *conn = mysql_init(NULL);

    // Connect to the MySQL database
    // Adjust the following parameters as needed:
    // host, user, password, database
    if (!mysql_real_connect(conn, "localhost", "root", "", "testdb", 0, NULL, 0)) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(conn));
        exit(EXIT_FAILURE);
    }

    // Construct the SQL query using string interpolation
    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Execute the query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "Query failed: %s\n", mysql_error(conn));
        exit(EXIT_FAILURE);
    }

    // Close the connection
    mysql_close(conn);
}

// Example usage
int main() {
    insertion("Alice", 22);
    printf("Student inserted successfully.\n");
    return 0;
}