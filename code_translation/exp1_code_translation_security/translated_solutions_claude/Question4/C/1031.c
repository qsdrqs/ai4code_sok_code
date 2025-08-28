#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

void insert(char* name, int age) {
    MYSQL *con;
    MYSQL_RES *result;
    MYSQL_ROW row;
    
    // Initialize MySQL connection
    con = mysql_init(NULL);
    
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }
    
    // Connect to database
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", 
                          "STUDENTS", 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }
    
    printf("Connected!\n");
    
    // Prepare SQL statement with proper formatting
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO students (name, age) VALUES ('%s', %d)", name, age);
    
    // Execute query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "Query failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }
    
    printf("1 record inserted\n");
    
    // Close connection
    mysql_close(con);
}