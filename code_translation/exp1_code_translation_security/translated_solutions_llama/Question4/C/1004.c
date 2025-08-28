#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to add a student to the STUDENTS table
char* add_student(const char* name, int age) {
    // Create a SQL query string
    char* sql = (char*) malloc(strlen("INSERT INTO STUDENTS (NAME, AGE) VALUES ('')") + strlen(name) + 10);
    sprintf(sql, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    printf("%s\n", sql);
    return sql;
}

int main() {
    MYSQL* conn;
    MYSQL_RES* res;
    MYSQL_ROW row;

    // Initialize MySQL library
    mysql_library_init(0, NULL, NULL);

    // Create a new MySQL connection
    conn = mysql_init(NULL);

    if (conn == NULL) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        mysql_library_end();
        exit(1);
    }

    // Connect to MySQL server
    if (mysql_real_connect(conn, "localhost", "yourusername", "yourpassword", NULL, 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        mysql_close(conn);
        mysql_library_end();
        exit(1);
    }

    printf("Connected!\n");

    // Add a student to the STUDENTS table
    char* sql = add_student("XXXXX", 0);
    int result = mysql_query(conn, sql);
    free(sql);

    if (result != 0) {
        fprintf(stderr, "%s\n", mysql_error(conn));
    } else {
        printf("Result: %lu\n", mysql_affected_rows(conn));
    }

    // Close MySQL connection
    mysql_close(conn);

    // End MySQL library
    mysql_library_end();

    return 0;
}