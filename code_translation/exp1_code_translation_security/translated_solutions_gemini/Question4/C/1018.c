#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // The core MySQL C API header

// --- Dependency Simulation ---
// In the JS code, this was `require('./config.js')`.
// Here, we define the connection details directly.
// In a real application, you would read these from a configuration file.
#define DB_HOST "127.0.0.1"
#define DB_USER "your_username"
#define DB_PASS "your_password"
#define DB_NAME "your_database"

/**
 * @brief Inserts a new record into the 'todos' table.
 *
 * @param name The value for the 'title' column.
 * @param age The value for the 'completed' column. (Note: The JS code uses 'age'
 *            for a column named 'completed', which is unusual. We follow that logic).
 * @param table This parameter was in the original JS function signature but was
 *              unused. It is included here for consistency but is also unused.
 */
void add(const char* name, int age, const char* table) {
    // Unused parameter, as in the original JS code.
    (void)table;

    MYSQL *conn;
    MYSQL_STMT *stmt;
    MYSQL_BIND bind[2];

    // In JS: let mysql = require('mysql');
    // In C: We initialize the MySQL library connection handler.
    conn = mysql_init(NULL);

    if (conn == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    // In JS: let connection = mysql.createConnection(config);
    // In C: We connect to the database using the credentials.
    if (mysql_real_connect(conn, DB_HOST, DB_USER, DB_PASS, DB_NAME, 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed: %s\n", mysql_error(conn));
        mysql_close(conn);
        exit(1);
    }

    // In JS: let stmt = `INSERT INTO todos(title,completed) VALUES ? `;
    // In C: We use a prepared statement with '?' placeholders for safety.
    const char *sql = "INSERT INTO todos(title, completed) VALUES (?, ?)";
    stmt = mysql_stmt_init(conn);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed: %s\n", mysql_error(conn));
        mysql_close(conn);
        exit(1);
    }

    if (mysql_stmt_prepare(stmt, sql, strlen(sql))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(conn);
        exit(1);
    }

    // In JS: let todos = [name, age]; connection.query(stmt, [todos], ...);
    // In C: We bind the C variables to the '?' placeholders.
    memset(bind, 0, sizeof(bind));

    // Bind parameter 1: name (string)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char *)name;
    bind[0].buffer_length = strlen(name);

    // Bind parameter 2: age (integer)
    bind[1].buffer_type = MYSQL_TYPE_LONG; // For integer types
    bind[1].buffer = (char *)&age;
    bind[1].is_unsigned = 0;
    bind[1].error = 0;

    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(conn);
        exit(1);
    }

    // Execute the prepared statement
    if (mysql_stmt_execute(stmt)) {
        // This block is equivalent to the `if (err)` check in the JS callback.
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(conn);
        return; // Use return instead of exit to allow the program to continue
    }

    // In JS: console.log('Row inserted:' + results.affectedRows);
    // In C: Get the number of affected rows from the connection handler.
    my_ulonglong affected_rows = mysql_stmt_affected_rows(stmt);
    printf("Row inserted: %llu\n", affected_rows);

    // --- Cleanup ---
    // The JS code implicitly handles cleanup, but in C, it must be explicit.
    mysql_stmt_close(stmt);
    mysql_close(conn);
}

// A main function to demonstrate how to call the `add` function.
int main() {
    printf("Attempting to add a record to the database...\n");
    // Call the function with sample data
    add("Learn C with MySQL", 1, "todos");
    return 0;
}