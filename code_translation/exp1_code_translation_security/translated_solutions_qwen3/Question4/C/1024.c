#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    // Initialize MySQL connection handle
    MYSQL *con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    // Connect to the database
    if (mysql_real_connect(
            con,                // Connection handle
            "127.0.0.1",        // Host
            "yourusername",     // Username
            "yourpassword",     // Password
            "mydb",             // Database name
            0,                  // Port (0 means default)
            NULL,               // Unix socket (NULL means default)
            0                   // Client flags
        ) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    // Prepare the SQL statement
    MYSQL_STMT *stmt = mysql_stmt_init(con);
    if (stmt == NULL) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    const char *sql = "INSERT INTO customers (name, address) VALUES (?, ?)";
    if (mysql_stmt_prepare(stmt, sql, strlen(sql)) != 0) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Define the values to insert
    char *name = "XXXXX";
    char *address = "XXXXX";

    // Bind the parameters
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    // First parameter: name
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = name;
    bind[0].buffer_length = strlen(name);

    // Second parameter: address
    bind[1].buffer_type = MYSQL_TYPE_STRING;
    bind[1].buffer = address;
    bind[1].buffer_length = strlen(address);

    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Execute the prepared statement
    if (mysql_stmt_execute(stmt) != 0) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Get the number of affected rows
    long long affected_rows = mysql_stmt_affected_rows(stmt);
    printf("Number of records inserted: %lld\n", affected_rows);

    // Clean up
    mysql_stmt_close(stmt);
    mysql_close(con);

    return 0;
}