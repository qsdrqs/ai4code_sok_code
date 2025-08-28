#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

// Global connection variable (equivalent to JavaScript's 'connection')
MYSQL *connection;

void addStudent(const char* name, int age) {
    // Buffer to hold the SQL query string
    char sql[512];
    
    // Build the SQL query string (equivalent to JavaScript string concatenation)
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    
    // Execute the query
    if (mysql_query(connection, sql)) {
        // Error handling (equivalent to 'if (err) throw err')
        fprintf(stderr, "Error: %s\n", mysql_error(connection));
        exit(1);
    }
    
    // Success message (equivalent to console.log)
    printf("1 record inserted\n");
}

// Example initialization function for the MySQL connection
void initConnection() {
    connection = mysql_init(NULL);
    
    if (connection == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }
    
    // Connect to database (you'll need to provide actual connection details)
    if (mysql_real_connect(connection, "localhost", "username", "password", 
                          "database_name", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(connection);
        exit(1);
    }
}

// Cleanup function
void closeConnection() {
    if (connection) {
        mysql_close(connection);
    }
}