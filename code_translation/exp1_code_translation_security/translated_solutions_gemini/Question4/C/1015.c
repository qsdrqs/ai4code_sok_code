#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // This is the main MySQL C API header.

// Helper function to print the last MySQL error and exit.
void finish_with_error(MYSQL *con, MYSQL_STMT *stmt) {
    if (stmt) {
        fprintf(stderr, "Error: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
    }
    if (con) {
        fprintf(stderr, "Error: %s\n", mysql_error(con));
        mysql_close(con);
    }
    exit(1);
}

/**
 * @brief Inserts a new student record into the database.
 *
 * @param name The name of the student.
 * @param age The age of the student.
 */
void addStudent(const char *name, int age) {
    // --- Connection Details ---
    const char *host = "localhost";
    const char *user = "yourusername";
    const char *password = "yourpassword";
    const char *dbname = "mydb";
    unsigned int port = 3306; // Default MySQL port
    const char *unix_socket = NULL;
    unsigned long client_flag = 0;

    MYSQL *con = NULL;
    MYSQL_STMT *stmt = NULL;
    MYSQL_BIND bind[2];

    // --- Initialize Connection Handler ---
    con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    // --- Establish Connection ---
    // mysql_real_connect is the C equivalent of con.connect()
    if (mysql_real_connect(con, host, user, password, dbname, port, unix_socket, client_flag) == NULL) {
        // The JS code would "throw err", in C we print the error and exit.
        finish_with_error(con, NULL);
    }

    printf("Connected!\n");

    // --- Prepare SQL Statement ---
    // Using a prepared statement with '?' placeholders is the secure way to prevent SQL injection.
    const char *sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    stmt = mysql_stmt_init(con);
    if (!stmt) {
        finish_with_error(con, NULL);
    }

    if (mysql_stmt_prepare(stmt, sql, strlen(sql))) {
        finish_with_error(con, stmt);
    }

    // --- Bind Parameters ---
    // We create a MYSQL_BIND array to hold the data for the '?' placeholders.
    memset(bind, 0, sizeof(bind));

    // Parameter 1: NAME (string)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char *)name;
    bind[0].buffer_length = strlen(name);

    // Parameter 2: AGE (integer)
    bind[1].buffer_type = MYSQL_TYPE_LONG; // MYSQL_TYPE_LONG is used for C int
    bind[1].buffer = (char *)&age;
    bind[1].is_unsigned = 0;
    bind[1].length = 0;

    // Bind the parameters to the prepared statement
    if (mysql_stmt_bind_param(stmt, bind)) {
        finish_with_error(con, stmt);
    }

    // --- Execute Statement ---
    // This is the equivalent of con.query()
    if (mysql_stmt_execute(stmt)) {
        // The JS code would "throw err" in the callback, we do it here.
        finish_with_error(con, stmt);
    }

    // Check how many rows were affected.
    my_ulonglong affected_rows = mysql_stmt_affected_rows(stmt);
    if (affected_rows == 1) {
        printf("1 record inserted\n");
    } else {
        printf("%llu records inserted\n", affected_rows);
    }

    // --- Cleanup ---
    // It's crucial in C to free allocated resources.
    mysql_stmt_close(stmt);
    mysql_close(con);
}

// Example main function to demonstrate usage
int main(int argc, char *argv[]) {
    // You can run the program like: ./my_program "John Doe" 25
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <name> <age>\n", argv[0]);
        return 1;
    }

    const char *name = argv[1];
    int age = atoi(argv[2]); // Convert age from string to integer

    if (age <= 0) {
        fprintf(stderr, "Please provide a valid age.\n");
        return 1;
    }

    addStudent(name, age);

    return 0;
}