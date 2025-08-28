#include <mysql/mysql.h>
#include <stdio.h>

// Function to insert a student (as per the original JS code)
void insertStudent(char *name, int age) {
    // Suppress unused parameter warnings
    (void)name;
    (void)age;

    MYSQL *conn = mysql_init(NULL);
    if (conn == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }

    // Connect to the database
    // Replace the following with your actual database configuration
    if (mysql_real_connect(
        conn,
        "localhost",      // Host
        "root",           // Username
        "password",       // Password
        "school_db",      // Database name
        3306,             // Port
        NULL,             // Unix socket
        0                 // Client flag
    ) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed: %s\n", mysql_error(conn));
        mysql_close(conn);
        return;
    }

    // SQL statement to insert a fixed record
    const char *sql = "INSERT INTO STUDENTS (title, completed) VALUES ('Learn how to insert a new row', true)";

    // Execute the query
    if (mysql_query(conn, sql)) {
        fprintf(stderr, "mysql_query() failed: %s\n", mysql_error(conn));
    }

    // Close the connection
    mysql_close(conn);
}

// Entry point of the program
int main() {
    insertStudent("XXXXX", 0);
    return 0;
}