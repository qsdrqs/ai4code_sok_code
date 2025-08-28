/*
 *  Rough C equivalent of the original JavaScript snippet.
 *  All external headers / libraries (e.g. MySQL client library) are assumed
 *  to be available when you compile / link this file.
 *
 *  gcc -o demo main.c -lmysqlclient
 */

#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>

/* --------------------------------------------------------- */
/* Simple helper that prints an error, frees the handle and exits. */
static void die(MYSQL *db, const char *msg)
{
    fprintf(stderr, "%s: %s\n", msg, mysql_error(db));
    if (db)
        mysql_close(db);
    exit(EXIT_FAILURE);
}

/* --------------------------------------------------------- */
/* “insertion” – C counterpart of the JavaScript function     */
void insertion(const char *name, int age)
{
    /* Create / initialise connection handle ---------------- */
    MYSQL *db = mysql_init(NULL);
    if (!db)
        die(db, "mysql_init() failed");

    /* Establish the connection ----------------------------- */
    /* Change the credentials / host / database as necessary */
    if (!mysql_real_connect(db,
                            "localhost",   /* host     */
                            "user",        /* user     */
                            "password",    /* pass     */
                            "database",    /* schema   */
                            0, NULL, 0))   /* port / socket / flags */
    {
        die(db, "mysql_real_connect() failed");
    }

    /* Build and execute the INSERT statement --------------- */
    char query[256];
    snprintf(query, sizeof(query),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);

    if (mysql_query(db, query))
        die(db, "INSERT failed");

    /* All good – close connection -------------------------- */
    mysql_close(db);
}

/* --------------------------------------------------------- */
int main(void)
{
    insertion("Alice", 21);
    insertion("Bob",   19);
    return 0;
}