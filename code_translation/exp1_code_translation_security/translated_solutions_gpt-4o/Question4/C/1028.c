#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void addStudent(const char *name, int age) {
    MYSQL *conn;
    MYSQL_RES *res;
    MYSQL_ROW row;

    char query[256];

    // Initialize the MySQL connection
    conn = mysql_init(NULL);
    if (conn == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }

    // Connect to the database
    if (mysql_real_connect(conn, "host", "user", "password", "database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(conn);
        return;
    }

    // Create the SQL query
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS (name, age) VALUES ('%s', %d)", name, age);

    // Execute the SQL query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(conn));
    } else {
        printf("1 record inserted\n");
    }

    // Close the connection
    mysql_close(conn);
}

int main() {
    addStudent("John Doe", 20);
    return 0;
}