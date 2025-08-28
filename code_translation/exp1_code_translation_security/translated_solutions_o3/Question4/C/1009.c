/*  Assumes that the MySQL C client library (libmysqlclient)
 *  and the corresponding headers are available at build time.
 *  The symbols ‘host’, ‘user’, ‘pass’, ‘db’, ‘port’ (or an
 *  already-opened MYSQL* handle called ‘con’) are expected to be
 *  supplied elsewhere in your code base, exactly like the
 *  JavaScript example relied on an existing “con” object.         */

#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ------------------------------------------------------------------
 * createEntry : Insert one record into the STUDENTS table.
 * ------------------------------------------------------------------ */
void createEntry(const char *name, int age)
{
    /* --------------------------------------------------------------
     * Obtain / open a connection.
     * (Replace the placeholders with real values or reuse
     *  an already-opened MYSQL* handle if you have one.)
     * -------------------------------------------------------------- */
    MYSQL *con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    if (mysql_real_connect(con,
                           "localhost",   /* host  */
                           "user",        /* user  */
                           "password",    /* pass  */
                           "database",    /* schema */
                           0,             /* port   */
                           NULL,          /* unix socket / defaults */
                           0) == NULL) {
        fprintf(stderr, "Connection error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    /* --------------------------------------------------------------
     * Create and prepare a parametrised INSERT statement.
     * -------------------------------------------------------------- */
    const char *sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
    MYSQL_STMT *stmt = mysql_stmt_init(con);
    if (stmt == NULL) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    if (mysql_stmt_prepare(stmt, sql, (unsigned long)strlen(sql))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n",
                mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    /* --------------------------------------------------------------
     * Bind the parameters.
     * -------------------------------------------------------------- */
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    /* name -> VARCHAR/TEXT */
    bind[0].buffer_type   = MYSQL_TYPE_STRING;
    bind[0].buffer        = (void *)name;
    bind[0].buffer_length = (unsigned long)strlen(name);

    /* age -> INT */
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer      = (void *)&age;

    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n",
                mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    /* --------------------------------------------------------------
     * Execute the statement.
     * -------------------------------------------------------------- */
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n",
                mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    /* Clean up. */
    mysql_stmt_close(stmt);
    mysql_close(con);
}