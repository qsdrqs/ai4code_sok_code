#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

/* Assuming there's a SQL table called STUDENTS available */

/* Global MySQL connection (assumed to be initialized elsewhere,
   similar to the 'connection' variable in the JS version) */
extern MYSQL *connection;

/*
adds a new entry to SQL table "STUDENTS"
*/
void add(const char *name, int age) {
    char sql[512];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);
    printf("%s\n", sql);

    if (mysql_query(connection, sql) != 0) {
        fprintf(stderr, "Error: %s\n", mysql_error(connection));
        exit(EXIT_FAILURE);
    }

    printf("1 record inserted\n");
}