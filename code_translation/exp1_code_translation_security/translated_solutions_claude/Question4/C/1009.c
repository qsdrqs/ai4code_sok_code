#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>
#include <string.h>

// Global connection variable (assuming 'con' from original JS code)
MYSQL *con;

void createEntry(const char* name, int age) {
    // Connect to database
    if (mysql_real_connect(con, NULL, NULL, NULL, NULL, 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        exit(1);
    }
    
    // Prepare the SQL statement
    MYSQL_STMT *stmt;
    MYSQL_BIND bind[2];
    
    stmt = mysql_stmt_init(con);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        exit(1);
    }
    
    const char* query = "INSERT INTO STUDENTS (name, age) VALUES (?,?)";
    if (mysql_stmt_prepare(stmt, query, strlen(query))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        exit(1);
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
        exit(1);
    }
    
    // Execute the statement
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        exit(1);
    }
    
    // Clean up
    mysql_stmt_close(stmt);
}

// Example usage and initialization
int main() {
    // Initialize MySQL connection
    con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }
    
    // Example usage
    createEntry("John Doe", 25);
    
    // Close connection
    mysql_close(con);
    
    return 0;
}