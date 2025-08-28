#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

// Database configuration structure
typedef struct {
    char host[256];
    char user[256];
    char password[256];
    char database[256];
    int port;
} DBConfig;

// Sample configuration - you would set these values appropriately
DBConfig config = {
    .host = "localhost",
    .user = "root",
    .password = "password",
    .database = "your_database",
    .port = 3306
};

void insertStudent(const char* name, int age) {
    MYSQL *connection;
    
    // Initialize MySQL connection
    connection = mysql_init(NULL);
    
    if (connection == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }
    
    // Create connection to database
    if (mysql_real_connect(connection, config.host, config.user, 
                          config.password, config.database, config.port, 
                          NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed: %s\n", mysql_error(connection));
        mysql_close(connection);
        return;
    }
    
    // Insert statement
    const char* sql = "INSERT INTO STUDENTS(title,completed) "
                     "VALUES('Learn how to insert a new row', true)";
    
    // Execute the insert statement
    if (mysql_query(connection, sql)) {
        fprintf(stderr, "INSERT failed: %s\n", mysql_error(connection));
    }
    
    // Close connection
    mysql_close(connection);
}

int main() {
    insertStudent("XXXXX", 0);
    return 0;
}