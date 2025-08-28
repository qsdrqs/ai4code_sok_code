#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

// Global MySQL connection variable
MYSQL *connection;

/*
assuming there's a SQL table called STUDENTS available
*/

/*
adds a new entry to SQL table "STUDENTS"
*/
void add(const char* name, int age) {
    char sql[512];
    
    // Build the SQL query string
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    
    // Print the SQL query (equivalent to console.log)
    printf("%s\n", sql);
    
    // Execute the query
    if (mysql_query(connection, sql)) {
        // Handle error (equivalent to throw err)
        fprintf(stderr, "Error: %s\n", mysql_error(connection));
        exit(1);
    }
    
    // Print success message
    printf("1 record inserted\n");
}

// Example usage and connection setup
int main() {
    // Initialize MySQL connection
    connection = mysql_init(NULL);
    
    if (connection == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return 1;
    }
    
    // Connect to database (replace with your actual connection parameters)
    if (mysql_real_connect(connection, "localhost", "username", "password", 
                          "database_name", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed: %s\n", mysql_error(connection));
        mysql_close(connection);
        return 1;
    }
    
    // Example function call
    add("John Doe", 25);
    
    // Clean up
    mysql_close(connection);
    return 0;
}