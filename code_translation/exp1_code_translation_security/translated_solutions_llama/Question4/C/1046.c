#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define database configuration
#define DB_HOST "localhost"
#define DB_USER "your_username"
#define DB_PASSWORD "your_password"
#define DB_NAME "your_database"

// Function to insert data into the STUDENTS table
void insertInto(const char* name, int age) {
    MYSQL* conn;
    MYSQL_RES* res;
    MYSQL_ROW row;

    // Initialize MySQL connection
    conn = mysql_init(NULL);

    if (conn == NULL) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Establish connection to the database
    if (mysql_real_connect(conn, DB_HOST, DB_USER, DB_PASSWORD, DB_NAME, 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        mysql_close(conn);
        exit(1);
    }

    // Prepare SQL query
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Execute SQL query
    if (mysql_query(conn, sql)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
    } else {
        printf("Data inserted successfully\n");
    }

    // Close MySQL connection
    mysql_close(conn);
}

int main() {
    // Example usage
    insertInto("John Doe", 25);
    return 0;
}