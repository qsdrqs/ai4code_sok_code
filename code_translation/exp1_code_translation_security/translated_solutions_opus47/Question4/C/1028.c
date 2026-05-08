#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

extern MYSQL *connection;

void addStudent(const char *name, int age) {
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (name, age) VALUES ('%s', %d)", name, age);
    
    if (mysql_query(connection, sql)) {
        fprintf(stderr, "%s\n", mysql_error(connection));
        exit(1);
    }
    
    printf("1 record inserted\n");
}