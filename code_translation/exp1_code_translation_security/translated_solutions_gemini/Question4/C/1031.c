#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // The MySQL C API header

// Function to insert a new student record into the database.
void insert(const char* name, int age) {
    // --- Connection Details ---
    const char *server = "localhost";
    const char *user = "yourusername";
    const char *password = "yourpassword";
    const char *database = "STUDENTS";

    // --- Initialize Connection Handle ---
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    // --- Connect to the Database ---
    // mysql_real_connect is a synchronous operation.
    if (mysql_real_connect(con, server, user, password, database, 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection Error: %s\n", mysql_error(con));
        mysql_close(con); // Clean up the handle before exiting
        exit(1);
    }

    printf("Connected!\n");

    // --- Prepare the SQL Query ---
    // Note: Using snprintf is better than sprintf, but for production code,
    // prepared statements are the safest way to prevent SQL injection.
    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO students (name, age) VALUES ('%s', %d)", name, age);

    // --- Execute the SQL Query ---
    if (mysql_query(con, query)) {
        fprintf(stderr, "Query Error: %s\n", mysql_error(con));
        mysql_close(con); // Clean up the handle before exiting
        exit(1);
    }

    printf("1 record inserted\n");

    // --- Close the Connection ---
    mysql_close(con);
}

// Example main function to demonstrate usage
int main() {
    // Call the insert function with sample data
    insert("David", 24);
    return 0;
}