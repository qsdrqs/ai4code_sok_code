#include <stdio.h>
#include <string.h>
#include "connection.h"  /* provides Connection, QueryResult, Connection_new(), Connection_query() */

typedef struct {
    const char *name;
    int age;
} Student;

QueryResult *registerStudent(const char *name, int age) {
    Student student = { .name = name, .age = age };

    Connection *connection = Connection_new(); /* create connection here */

    char query[512];
    snprintf(query, sizeof(query),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')",
             student.name, student.age);

    return Connection_query(connection, query);
}