/*
 * File : insert_student.c
 * Build: cc insert_student.c -lmysqlclient -o insert_student
 */

#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>

/* ----------------------------------------------------------
 * insertInto
 * ----------------------------------------------------------
 * Rough equivalent of the original JavaScript function.
 * Opens a connection, inserts a row into STUDENTS, closes
 * the connection.
 * ---------------------------------------------------------- */
void insertInto(const char *name, int age)
{
    /* Create / initialise connection handle */
    MYSQL *connection = mysql_init(NULL);
    if (!connection) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }

    /* ---- CONNECT ----
       The actual host/user/password/db values come from your
       own configuration (mirrors `config` in the JS version).
    */
    if (!mysql_real_connect(connection,
                            "localhost",     /* host     */
                            "user",          /* user     */
                            "password",      /* pwd      */
                            "database",      /* schema   */
                            0,               /* port     */
                            NULL,            /* socket   */
                            0))              /* flags    */
    {
        fprintf(stderr, "Connection failed: %s\n",
                mysql_error(connection));
        mysql_close(connection);
        return;
    }

    /* ---- BUILD & EXECUTE QUERY ---- */
    char sql[512];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS(NAME,AGE) VALUES('%s', %d)",
             name, age);

    if (mysql_query(connection, sql)) {
        fprintf(stderr, "INSERT failed: %s\n",
                mysql_error(connection));
        /* fall through to cleanup */
    }

    /* ---- CLEANUP ---- */
    mysql_close(connection);
}