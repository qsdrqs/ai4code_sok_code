#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

void insertInto(const char *name, int age) {
    MYSQL *conn;
    MYSQL_RES *res;
    MYSQL_ROW row;

    // Initialize the MySQL connection
    conn = mysql_init(NULL);
    if (conn == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    // Connect to the database
    if (mysql_real_connect(conn, "host", "user", "password", "database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(conn);
        exit(EXIT_FAILURE);
    }

    // Prepare the SQL query
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS(NAME, AGE) VALUES ('%s', %d)", name, age);

    // Execute the SQL query
    if (mysql_query(conn, sql)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(conn));
    }

    // Close the connection
    mysql_close(conn);
}

int main() {
    // Example usage
    insertInto("John Doe", 25);
    return 0;
}