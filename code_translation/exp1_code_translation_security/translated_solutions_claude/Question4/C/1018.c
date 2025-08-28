#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>
#include "config.h"

void add(const char* name, int age, const char* table) {
    MYSQL *connection;
    MYSQL_STMT *stmt;
    MYSQL_BIND bind[2];
    
    // Initialize MySQL connection
    connection = mysql_init(NULL);
    if (connection == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }
    
    // Connect to database using config
    if (mysql_real_connect(connection, DB_HOST, DB_USER, DB_PASSWORD, 
                          DB_NAME, DB_PORT, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed: %s\n", mysql_error(connection));
        mysql_close(connection);
        return;
    }
    
    // Prepare the SQL statement
    const char* sql = "INSERT INTO todos(title,completed) VALUES (?, ?)";
    stmt = mysql_stmt_init(connection);
    if (stmt == NULL) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        mysql_close(connection);
        return;
    }
    
    if (mysql_stmt_prepare(stmt, sql, strlen(sql))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(connection);
        return;
    }
    
    // Initialize bind parameters
    memset(bind, 0, sizeof(bind));
    
    // Bind name parameter (title)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char*)name;
    bind[0].buffer_length = strlen(name);
    bind[0].is_null = 0;
    bind[0].length = 0;
    
    // Bind age parameter (completed)
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = (char*)&age;
    bind[1].is_null = 0;
    bind[1].length = 0;
    
    // Bind parameters to statement
    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(connection);
        return;
    }
    
    // Execute the statement
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(connection);
        return;
    }
    
    // Get number of affected rows
    my_ulonglong affected_rows = mysql_stmt_affected_rows(stmt);
    printf("Row inserted: %llu\n", affected_rows);
    
    // Clean up
    mysql_stmt_close(stmt);
    mysql_close(connection);
}