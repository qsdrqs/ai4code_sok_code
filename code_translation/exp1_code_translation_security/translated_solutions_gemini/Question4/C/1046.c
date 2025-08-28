#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // The main MySQL C API header

// --- Database Configuration ---
// Corresponds to the 'config' object in your JavaScript
#define DB_HOST "localhost"
#define DB_USER "your_username"
#define DB_PASS "your_password"
#define DB_NAME "your_database"

// A helper function for robust error handling
void finish_with_error(MYSQL *con) {
    fprintf(stderr, "%s\n", mysql_error(con));
    if (con != NULL) {
        mysql_close(con);
    }
    exit(1);
}

/**
 * @brief Inserts a new student record into the STUDENTS table.
 *
 * This function uses a prepared statement to safely insert data,
 * preventing SQL injection attacks.
 *
 * @param con An active MySQL connection handle.
 * @param name The name of the student (string).
 * @param age The age of the student (integer).
 */
void insertInto(MYSQL *con, const char *name, int age) {
    // 1. The SQL query with '?' as placeholders for data
    const char *query = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";

    // 2. Initialize a prepared statement
    MYSQL_STMT *stmt = mysql_stmt_init(con);
    if (!stmt) {
        finish_with_error(con);
    }

    // 3. Prepare the SQL query
    if (mysql_stmt_prepare(stmt, query, strlen(query))) {
        fprintf(stderr, "mysql_stmt_prepare failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    // 4. Bind the parameters to the placeholders
    MYSQL_BIND params[2];
    memset(params, 0, sizeof(params)); // Zero out the structure

    // Parameter 1: NAME (String)
    unsigned long name_length = strlen(name);
    params[0].buffer_type = MYSQL_TYPE_STRING;
    params[0].buffer = (char *)name;
    params[0].buffer_length = name_length;

    // Parameter 2: AGE (Integer)
    params[1].buffer_type = MYSQL_TYPE_LONG; // Use MYSQL_TYPE_LONG for int
    params[1].buffer = (char *)&age;
    params[1].is_unsigned = 0; // Age is a signed integer

    // Bind the MYSQL_BIND structure to the statement
    if (mysql_stmt_bind_param(stmt, params)) {
        fprintf(stderr, "mysql_stmt_bind_param failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    // 5. Execute the prepared statement
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    printf("Successfully inserted %s, age %d. (ID: %llu)\n", name, age, mysql_stmt_insert_id(stmt));

    // 6. Clean up the statement
    mysql_stmt_close(stmt);
}

int main() {
    // --- Connection Setup ---
    // Corresponds to mysql.createConnection(config)
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    // Connect to the database
    if (mysql_real_connect(con, DB_HOST, DB_USER, DB_PASS, DB_NAME, 0, NULL, 0) == NULL) {
        finish_with_error(con);
    }

    printf("MySQL connection successful.\n");

    // --- Using the function ---
    // Example calls to insert data
    insertInto(con, "Alice", 30);
    insertInto(con, "Bob", 25);
    
    // Example of a name that would be dangerous without prepared statements
    insertInto(con, "Robert'); DROP TABLE STUDENTS; --", 22);


    // --- Close the connection ---
    // Corresponds to connection.end()
    mysql_close(con);
    printf("MySQL connection closed.\n");

    return 0;
}