#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * @requires variable name {connection} corresponding to a mysql db connection
 */
void addStudent(MYSQL *connection, const char *name, int age) {
    MYSQL_STMT *stmt;
    MYSQL_BIND bind[2];
    const char *query = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?,?)";
    
    // Initialize the statement
    stmt = mysql_stmt_init(connection);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        return;
    }
    
    // Prepare the statement
    if (mysql_stmt_prepare(stmt, query, strlen(query))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }
    
    // Initialize bind structures
    memset(bind, 0, sizeof(bind));
    
    // Bind name parameter (string)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char *)name;
    bind[0].buffer_length = strlen(name);
    bind[0].is_null = 0;
    bind[0].length = 0;
    
    // Bind age parameter (integer)
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = (char *)&age;
    bind[1].is_null = 0;
    bind[1].length = 0;
    
    // Bind the parameters
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
    
    // Clean up
    mysql_stmt_close(stmt);
}