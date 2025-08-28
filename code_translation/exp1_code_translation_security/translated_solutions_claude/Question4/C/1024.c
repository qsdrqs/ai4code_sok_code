#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>
#include <string.h>

int main() {
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
    if (mysql_real_connect(con, "127.0.0.1", "yourusername", "yourpassword", 
                          "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }
    
    printf("Connected!\n");
    
    // Prepare SQL statement
    char *sql = "INSERT INTO customers (name, address) VALUES (?, ?)";
    MYSQL_STMT *stmt;
    MYSQL_BIND bind[2];
    
    stmt = mysql_stmt_init(con);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        mysql_close(con);
        exit(1);
    }
    
    if (mysql_stmt_prepare(stmt, sql, strlen(sql))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(1);
    }
    
    // Data to insert (equivalent to the JavaScript values array)
    char name[] = "XXXXX";
    char address[] = "XXXXX";
    
    // Initialize bind parameters
    memset(bind, 0, sizeof(bind));
    
    // Bind name parameter
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = name;
    bind[0].buffer_length = strlen(name);
    
    // Bind address parameter
    bind[1].buffer_type = MYSQL_TYPE_STRING;
    bind[1].buffer = address;
    bind[1].buffer_length = strlen(address);
    
    // Bind parameters to statement
    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(1);
    }
    
    // Execute the statement
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(1);
    }
    
    // Get number of affected rows
    my_ulonglong affected_rows = mysql_stmt_affected_rows(stmt);
    printf("Number of records inserted: %llu\n", affected_rows);
    
    // Clean up
    mysql_stmt_close(stmt);
    mysql_close(con);
    
    return 0;
}