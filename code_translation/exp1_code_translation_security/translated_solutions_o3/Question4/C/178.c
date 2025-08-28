/*
 * register_student.c
 *
 * A direct C translation of the JavaScript function
 *
 *     function registerStudent(name, age) { … }
 */

#include <stdio.h>    /* snprintf */
#include <stdlib.h>   /* malloc / free */
#include <string.h>   /* strdup */

/* ------------------------------------------------------------------
 * External “dependency” stubs.
 * These types / functions are assumed to be provided by the project’s
 * database layer and are therefore only forward-declared here.
 * ------------------------------------------------------------------ */
typedef struct Connection Connection;
typedef struct Result     Result;

Connection *connection_create(void);               /* open connection   */
Result     *connection_query(Connection *,         /* run SQL statement */
                             const char *sql);
void        connection_destroy(Connection *);      /* close connection  */
/* ------------------------------------------------------------------ */

/* Local data structure mirroring the JavaScript object literal. */
typedef struct
{
    char *name;
    int   age;
} Student;

/*
 * registerStudent — inserts a new row into the STUDENTS table.
 *
 * Arguments
 *   name : zero-terminated string with the student’s name
 *   age  : student’s age
 *
 * Returns
 *   Pointer to a Result object (details depend on the DB layer).
 */
Result *registerStudent(const char *name, int age)
{
    /* 1. Build the Student struct (mirrors JS object literal). */
    Student student;
    student.name = strdup(name);   /* allocate & copy name */
    student.age  = age;

    /* 2. Obtain a database connection. */
    Connection *conn = connection_create();   /* provided elsewhere */

    /* 3. Compose the SQL INSERT statement. */
    char query[512];   /* ample space for modest inputs               */
    snprintf(query, sizeof(query),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')",
             student.name, student.age);

    /* 4. Execute the query and capture the result. */
    Result *res = connection_query(conn, query);

    /* 5. Clean-up: free heap memory and close the connection. */
    free(student.name);
    connection_destroy(conn);

    /* 6. Return whatever the DB layer gives us. */
    return res;
}