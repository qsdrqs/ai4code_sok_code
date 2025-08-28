#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

// Global connection variable (equivalent to the JavaScript connection)
MYSQL *connection = NULL;

// Function to create database connection (equivalent to mysql.createConnection(config))
MYSQL* createConnection(const char* host, const char* user, const char* password, const char* database) {
    MYSQL *conn = mysql_init(NULL);
    
    if (conn == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return NULL;
    }
    
    if (mysql_real_connect(conn, host, user, password, database, 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed: %s\n", mysql_error(conn));
        mysql_close(conn);
        return NULL;
    }
    
    return conn;
}

// Translated insertInto function
void insertInto(const char* name, int age) {
    if (connection == NULL) {
        fprintf(stderr, "Database connection not established\n");
        return;
    }
    
    // Create SQL query string (equivalent to template literal)
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS(NAME,AGE) VALUES ('%s', %d)", name, age);
    
    // Execute query (equivalent to connection.query(sql))
    if (mysql_query(connection, sql)) {
        fprintf(stderr, "INSERT failed: %s\n", mysql_error(connection));
        return;
    }
    
    // Close connection (equivalent to connection.end())
    mysql_close(connection);
    connection = NULL;
}

// Example usage:
int main() {
    // Initialize MySQL library
    if (mysql_library_init(0, NULL, NULL)) {
        fprintf(stderr, "Could not initialize MySQL library\n");
        return 1;
    }
    
    // Create connection (replace with your actual database credentials)
    connection = createConnection("localhost", "username", "password", "database_name");
    
    if (connection == NULL) {
        mysql_library_end();
        return 1;
    }
    
    // Use the function
    insertInto("John Doe", 25);
    
    // Cleanup
    mysql_library_end();
    return 0;
}