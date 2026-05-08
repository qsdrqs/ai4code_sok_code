#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* addStudent(const char* name, int age) {
    // Allocate enough space for the SQL string:
    // length of name + space for SQL syntax, age (int), and null terminator
    size_t size = strlen(name) + 100;
    char* sql = (char*)malloc(size);
    if (sql == NULL) {
        return NULL;
    }
    snprintf(sql, size, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    return sql;
}

//addStudent("XXXXX", 0);