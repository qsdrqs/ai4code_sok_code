/* 
 * Equivalent C implementation of the JavaScript function “add”.
 * All external symbols (e.g. `connection_query`) are assumed to be
 * provided elsewhere at link-time.
 */

#include <stdio.h>
#include <stdlib.h>

/*  
 *  External function supplied elsewhere.
 *  Should return 0 on success, non-zero on error.
 */
extern int connection_query(const char *sql);

/*
 *  Adds a new entry to the SQL table "STUDENTS".
 */
void add(const char *name, int age)
{
    /* Build the SQL statement. 256 bytes is usually enough; enlarge if needed. */
    char sql[256];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);

    /* Echo the generated statement (mimics console.log in JS). */
    puts(sql);

    /* Execute the statement and check for errors. */
    if (connection_query(sql) != 0)
    {
        perror("SQL insert failed");
        exit(EXIT_FAILURE);
    }

    puts("1 record inserted");
}