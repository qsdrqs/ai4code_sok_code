/* add_student.c */

#include <stdio.h>
#include <stdlib.h>
#include <mysql.h>          /* or whatever DB connector you are using */

/* The connection handle will be supplied by the surrounding code. */
extern MYSQL *connection;

/*
 * Inserts one student into the STUDENTS table.
 * name : student’s name
 * age  : student’s age
 */
void addStudent(const char *name, int age)
{
    /* Compose the SQL statement. 256 bytes is plenty for this simple query,
       but feel free to adjust as needed. */
    char sql[256];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (name, age) VALUES ('%s', %d)",
             name, age);

    /* Execute the query and check for errors. */
    if (mysql_query(connection, sql))
    {
        fprintf(stderr, "Insert failed: %s\n", mysql_error(connection));
        exit(EXIT_FAILURE);
    }

    printf("1 record inserted\n");
}