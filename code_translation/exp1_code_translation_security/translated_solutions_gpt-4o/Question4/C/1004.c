#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

// Function to add a student
char* add_student(const char* name, int age) {
    // Allocate enough space for the SQL query
    char* sql = (char*)malloc(256);
    if (sql == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }
    // Create the SQL query
    snprintf(sql, 256, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    printf("%s\n", sql);
    return sql;
}

int main() {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    // Connect to the database
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", NULL, 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(con);
        exit(1);
    }

    printf("Connected!\n");

    // Create the SQL query
    char* query = add_student("XXXXX", 0);

    // Execute the query
    if (mysql_query(con, query)) {
        fprintf(stderr, "Query failed: %s\n", mysql_error(con));
        free(query);
        mysql_close(con);
        exit(1);
    }

    printf("Result: %s\n", query);

    // Clean up
    free(query);
    mysql_close(con);

    return 0;
}