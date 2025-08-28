#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // Include the MySQL C Connector header

// --- Database Connection Details ---
// IMPORTANT: Replace with your actual database credentials.
const char *DB_HOST = "127.0.0.1";
const char *DB_USER = "your_username";
const char *DB_PASS = "your_password";
const char *DB_NAME = "your_database";
const unsigned int DB_PORT = 3306;

/**
 * @brief Finishes the connection and prints a detailed error message.
 * @param con A pointer to the MYSQL connection object.
 */
void finish_with_error(MYSQL *con) {
    fprintf(stderr, "%s\n", mysql_error(con));
    if (con != NULL) {
        mysql_close(con);
    }
    exit(1);
}

/**
 * @brief Inserts a new student record into the database.
 *
 * This function connects to the database, prepares a secure SQL statement,
 * binds the provided parameters, executes the query, and then closes the connection.
 *
 * @param name The name of the student (string).
 * @param age The age of the student (integer).
 */
void insertion(const char *name, int age) {
    // The JavaScript `var mysql = require('mysql');` is analogous to including
    // the header and linking the library.

    // The JavaScript `var con = mysql.createConnection();` is analogous to mysql_init().
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    // The JavaScript `con.connect(function(err){...})` is analogous to mysql_real_connect().
    // In C, error handling is done by checking the return value, not with a callback.
    if (mysql_real_connect(con, DB_HOST, DB_USER, DB_PASS, DB_NAME, DB_PORT, NULL, 0) == NULL) {
        finish_with_error(con);
    }

    printf("Successfully connected to database.\n");

    // The JavaScript `var sql = INSERT INTO ...` is defined here.
    // We use '?' as placeholders to prevent SQL injection.
    const char *sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    // Prepare the SQL statement
    MYSQL_STMT *stmt = mysql_stmt_init(con);
    if (!stmt) {
        finish_with_error(con);
    }

    if (mysql_stmt_prepare(stmt, sql, strlen(sql)) != 0) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    // Bind the parameters to the placeholders
    MYSQL_BIND params[2];
    memset(params, 0, sizeof(params)); // Zero out the structure

    // Parameter 1: NAME (String)
    unsigned long name_length = strlen(name);
    params[0].buffer_type = MYSQL_TYPE_STRING;
    params[0].buffer = (char *)name;
    params[0].buffer_length = name_length;

    // Parameter 2: AGE (Integer)
    params[1].buffer_type = MYSQL_TYPE_LONG; // MYSQL_TYPE_LONG is used for C int
    params[1].buffer = (char *)&age;

    // Bind the parameter structures to the statement
    if (mysql_stmt_bind_param(stmt, params) != 0) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    // Execute the prepared statement
    if (mysql_stmt_execute(stmt) != 0) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    printf("Successfully inserted record: Name='%s', Age=%d\n", name, age);

    // Clean up
    mysql_stmt_close(stmt);
    mysql_close(con);
}

// A main function to demonstrate how to use the `insertion` function.
int main() {
    printf("Attempting to insert 'Alice'...\n");
    insertion("Alice", 22);

    printf("\nAttempting to insert 'Bob'...\n");
    insertion("Bob", 35);

    return 0;
}