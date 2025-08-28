#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Generates an SQL INSERT statement for a student.
 *
 * @param name The name of the student.
 * @param age The age of the student.
 * @return A dynamically allocated SQL string, or NULL on failure.
 */
char* add_student(const char* name, int age) {
    // Estimate the required buffer size:
    // Static parts of the SQL string: "INSERT INTO STUDENTS (NAME, AGE) VALUES ('', 0)"
    // Length of static parts: 45 characters
    // Max digits for age (including negative): 11
    // +1 for null terminator
    size_t name_len = strlen(name);
    size_t len = 45 + name_len + 11 + 1;

    char* sql = malloc(len);
    if (!sql) {
        perror("malloc");
        return NULL;
    }

    snprintf(sql, len, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    printf("%s\n", sql);

    return sql;
}

int main() {
    MYSQL* con = mysql_init(NULL);
    if (!con) {
        fprintf(stderr, "mysql_init failed\n");
        return EXIT_FAILURE;
    }

    // Connect to the MySQL server
    if (!mysql_real_connect(con, "localhost", "yourusername", "yourpassword", NULL, 0, NULL, 0)) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        return EXIT_FAILURE;
    }

    printf("Connected!\n");

    // Generate the SQL statement
    char* sql = add_student("XXXXX", 0);
    if (!sql) {
        mysql_close(con);
        return EXIT_FAILURE;
    }

    // Execute the query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "Query failed: %s\n", mysql_error(con));
        free(sql);
        mysql_close(con);
        return EXIT_FAILURE;
    }

    printf("Result: Success\n");

    // Clean up
    free(sql);
    mysql_close(con);

    return 0;
}