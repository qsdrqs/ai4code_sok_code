/* add_student.c */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/*  Returns a freshly-allocated SQL statement of the form
 *  INSERT INTO STUDENTS (NAME, AGE) VALUES ('<name>', <age>)
 *  The caller is responsible for freeing the returned buffer.
 */
char *addStudent(const char *name, int age)
{
    /* Constant text fragments */
    const char *prefix = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('";
    const char *middle = "', ";
    const char *suffix = ")";

    /* Convert age -> string */
    char ageStr[12];                       /* enough for 32-bit int */
    snprintf(ageStr, sizeof(ageStr), "%d", age);

    /* Compute total length */
    size_t len = strlen(prefix) + strlen(name) +
                 strlen(middle) + strlen(ageStr) +
                 strlen(suffix) + 1;       /* +1 for '\0' */

    /* Allocate result buffer */
    char *sql = (char *)malloc(len);
    if (!sql) return NULL;                 /* allocation failed */

    /* Build the final statement */
    snprintf(sql, len, "%s%s%s%s%s",
             prefix, name, middle, ageStr, suffix);

    return sql;                            /* caller must free() */
}


/* Example usage / quick test
 * Compile:  gcc -DTEST_ADD_STUDENT add_student.c -o demo
 */
#ifdef TEST_ADD_STUDENT
int main(void)
{
    char *query = addStudent("XXXXX", 0);
    if (query) {
        puts(query);
        free(query);
    }
    return 0;
}
#endif