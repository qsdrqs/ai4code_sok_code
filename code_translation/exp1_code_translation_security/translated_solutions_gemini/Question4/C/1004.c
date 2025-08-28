#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // Include the MySQL C Connector header

// --- Dependency Information ---
// This code depends on the MySQL C Connector (also known as libmysqlclient).
// You must have it installed on your system.

// --- Database Connection Details ---
// These correspond to the mysql.createConnection({...}) object in JS.
#define DB_HOST "localhost"
#define DB_USER "yourusername"
#define DB_PASS "yourpassword"
#define DB_NAME "your_database" // NOTE: A database must be selected to run queries.

/**
 * @brief C function to add a student to the database.
 *
 * This function securely adds a new entry to the STUDENTS table using a prepared statement
 * to prevent SQL injection. It corresponds to the `add_student` function in JavaScript.
 *
 * @param con An active MySQL connection handle.
 * @param name The name of the student.
 * @param age The age of the student.
 * @return int 0 on success, 1 on failure.
 */
int add_student(MYSQL *con, const char *name, int age) {
    // The original JS function built a raw SQL string, which is insecure.
    // The C equivalent of that would be using sprintf(), but the correct,
    // secure method is a prepared statement, shown here.
    const char *sql_template = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    
    // In JS, `console.log(sql)` printed the full query. With prepared statements,
    // the final query is assembled on the server, not the client. We can only
    // print the template.
    printf("Preparing SQL: %s\n", sql_template);

    MYSQL_STMT *stmt;
    MYSQL_BIND params[2];
    int name_length = strlen(name);

    // 1. Initialize a statement handle
    stmt = mysql_stmt_init(con);
    if (!stmt) {
        fprintf(stderr, "Error: mysql_stmt_init() failed\n");
        return 1;
    }

    // 2. Prepare the SQL statement
    if (mysql_stmt_prepare(stmt, sql_template, strlen(sql_template))) {
        fprintf(stderr, "Error: mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return 1;
    }

    // 3. Bind the parameters to the statement markers (?)
    memset(params, 0, sizeof(params)); // Zero out the structure

    // Parameter 1: name (string)
    params[0].buffer_type = MYSQL_TYPE_STRING;
    params[0].buffer = (char *)name;
    params[0].buffer_length = name_length;

    // Parameter 2: age (integer)
    params[1].buffer_type = MYSQL_TYPE_LONG; // int in C corresponds to MYSQL_TYPE_LONG
    params[1].buffer = (char *)&age;
    params[1].is_null = 0;

    if (mysql_stmt_bind_param(stmt, params)) {
        fprintf(stderr, "Error: mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return 1;
    }

    // 4. Execute the prepared statement
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "Error: mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return 1;
    }

    // 5. Clean up the statement handle
    mysql_stmt_close(stmt);

    return 0; // Success
}

int main() {
    // This section corresponds to the main execution block in the JS file.
    
    // Initialize the connection handle, like `mysql.createConnection`
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    // Connect to the database, like `con.connect()`
    if (mysql_real_connect(con, DB_HOST, DB_USER, DB_PASS, DB_NAME, 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }

    // This corresponds to the `if (err) throw err; console.log("Connected!");` block
    printf("Connected!\n");

    // This section corresponds to `con.query(add_student(...), ...)`
    if (add_student(con, "John Doe", 30) == 0) {
        // The JS `result` object is complex. For an INSERT, the most useful
        // information is the number of affected rows.
        my_ulonglong affected_rows = mysql_affected_rows(con);
        printf("Result: %llu row(s) affected.\n", affected_rows);
    } else {
        // This corresponds to the `if (err) throw err;` inside the query callback
        fprintf(stderr, "Query failed: %s\n", mysql_error(con));
    }

    // Close the connection
    mysql_close(con);

    return 0;
}