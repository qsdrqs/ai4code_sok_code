#include <stdio.h>   /* snprintf */
#include <string.h>  /* strlen   */

/*  Assume the following symbols are provided elsewhere:
 *
 *      typedef struct DB DB;          // Opaque database handle
 *      int  db_run(DB *db, const char *sql);  // Executes an SQL command
 *
 *  (The concrete definitions/implementations will come from the
 *   database layer you link against, so they are not repeated here.)
 */

/*
 *  Given an SQL table named "STUDENTS" with columns "NAME" and "AGE",
 *  add a new entry.
 *
 *  Parameters
 *      name : null-terminated string holding the student’s name
 *      age  : student’s age
 *      db   : pointer to an open database connection
 */
void addEntry(const char *name, int age, DB *db)
{
    /* Compose the INSERT statement. 512 bytes is plenty for this example,
       but you can enlarge it or make it dynamic if you expect longer names. */
    char sql[512];

    /* Build: INSERT INTO STUDENTS (NAME, AGE) VALUES ('<name>', <age>) */
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);

    /* Execute the statement.  Error handling omitted for brevity. */
    db_run(db, sql);
}