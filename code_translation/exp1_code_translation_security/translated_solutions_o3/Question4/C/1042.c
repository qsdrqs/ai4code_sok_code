#include <stdio.h>
#include <stdlib.h>

/*  Assume that:
    1. A valid database connection object named `connection`
       (or whatever is required by your DB library) is already
       available and initialized elsewhere in the program.
    2. The function `connection_query(const char *sql)` executes an
       SQL statement and returns 0 on success, non-zero on error.
    3. The function `connection_error()` returns a C-string describing
       the most recent error.
    These utilities will be provided in the build environment, so we
    only focus on translating the logic itself.                       */

void addStudent(const char *name, int age)
{
    /* Build the SQL statement.  (Size chosen for example; enlarge if
       you expect very long names.)                                   */
    char sql[256];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);

    /* Execute the query and check for errors. */
    if (connection_query(sql) != 0) {
        fprintf(stderr, "Error inserting record: %s\n", connection_error());
        /* Re-raise the error just like `throw err` in JavaScript.     */
        exit(EXIT_FAILURE);
    }

    printf("1 record inserted\n");
}