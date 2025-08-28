#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>
#include <string.h>

// Assuming connection is a global variable or passed as parameter
// For this example, I'll assume it's global
MYSQL *connection;

void add(const char* name, int age) {
    /* Add name, age to students table in MySQL */
    const char* query = "INSERT INTO students (name, age) VALUES (?, ?)";
    
    MYSQL_STMT *stmt;
    MYSQL_BIND bind[2];
    
    // Prepare the statement
    stmt = mysql_stmt_init(connection);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        return;
    }
    
    if (mysql_stmt_prepare(stmt, query, strlen(query))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }
    
    // Initialize bind parameters
    memset(bind, 0, sizeof(bind));
    
    // Bind name parameter (string)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char*)name;
    bind[0].buffer_length = strlen(name);
    bind[0].is_null = 0;
    bind[0].length = 0;
    
    // Bind age parameter (integer)
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = (char*)&age;
    bind[1].is_null = 0;
    bind[1].length = 0;
    
    // Bind parameters to statement
    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }
    
    // Execute the statement
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }
    
    printf("Added %s to students table\n", name);
    
    // Clean up
    mysql_stmt_close(stmt);
}