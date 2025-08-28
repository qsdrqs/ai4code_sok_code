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
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (name, age) VALUES ('%s', %d)", name, age);
    
    // Execute the query
    if (mysql_query(connection, sql)) {
        // Error handling (equivalent to 'if (err) throw err')
        fprintf(stderr, "Error: %s\n", mysql_error(connection));
        exit(1);
    }
    
    // Success message (equivalent to console.log)
    printf("1 record inserted\n");
}

// Example usage and setup function
int main() {
    // Initialize MySQL connection
    connection = mysql_init(NULL);
    
    if (connection == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return 1;
    }
    
    // Connect to database (you'll need to adjust these parameters)
    if (mysql_real_connect(connection, "localhost", "username", "password", 
                          "database_name", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(connection);
        return 1;
    }
    
    // Example function call
    addStudent("John Doe", 20);
    
    // Clean up
    mysql_close(connection);
    return 0;
}