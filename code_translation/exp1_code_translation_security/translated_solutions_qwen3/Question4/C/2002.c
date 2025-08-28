#include <mysql.h>
#include <string.h>

// Assume that the global MYSQL* connection is already initialized and connected
extern MYSQL* connection;

void addStudent(const char* name, int age) {
    // Initialize a prepared statement
    MYSQL_STMT* stmt = mysql_stmt_init(connection);
    if (!stmt) {
        return; // Handle error if needed, but original JS code ignores it
    }

    // Define the SQL query with placeholders
    const char* query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    if (mysql_stmt_prepare(stmt, query, strlen(query)) != 0) {
        mysql_stmt_close(stmt);
        return; // Handle error if needed
    }

    // Prepare the parameter binding array
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind)); // Initialize all fields to 0

    // Bind the first parameter: name (string)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (void*)name;
    bind[0].buffer_length = strlen(name);
    bind[0].is_null = 0;

    // Bind the second parameter: age (integer)
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = (void*)&age;
    bind[1].is_null = 0;

    // Bind the parameters to the prepared statement
    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        mysql_stmt_close(stmt);
        return; // Handle error if needed
    }

    // Execute the prepared statement
    mysql_stmt_execute(stmt);

    // Clean up the statement
    mysql_stmt_close(stmt);
}