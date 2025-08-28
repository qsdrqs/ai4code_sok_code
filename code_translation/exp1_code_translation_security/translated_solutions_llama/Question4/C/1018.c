#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// Function to add a new todo item to the database
void add(const char* name, int age) {
    // MySQL configuration
    MYSQL* conn;
    MYSQL_RES* res;
    MYSQL_ROW row;

    // Initialize MySQL connection
    conn = mysql_init(NULL);

    if (conn == NULL) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // MySQL configuration (replace with your actual config)
    char* host = "localhost";
    char* user = "your_username";
    char* password = "your_password";
    char* db = "your_database";

    // Connect to MySQL server
    if (mysql_real_connect(conn, host, user, password, db, 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        mysql_close(conn);
        exit(1);
    }

    // Prepare query
    char query[256];
    sprintf(query, "INSERT INTO todos (title, completed) VALUES ('%s', %d)", name, age);

    // Execute query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
    } else {
        // Get inserted rows
        printf("Rows affected: %lu\n", mysql_affected_rows(conn));
    }

    // Close MySQL connection
    mysql_close(conn);
}

int main() {
    // Example usage
    add("New Todo Item", 0);
    return 0;
}