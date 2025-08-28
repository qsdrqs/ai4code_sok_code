/*
 * Translation of the given JavaScript into straight-forward C.
 * Compile with something similar to
 *      gcc main.c -lmysqlclient
 */

#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>     /* MySQL Connector/C header */

void add_student(const char *name, int age)
{
    /* ------------------------------------------------------------------ */
    /* 1.  Allocate and initialise the connection handle                  */
    /* ------------------------------------------------------------------ */
    MYSQL *con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    /* ------------------------------------------------------------------ */
    /* 2.  Connect (fill in your own credentials / database parameters)   */
    /* ------------------------------------------------------------------ */
    const char *host     = "localhost";
    const char *user     = "user";
    const char *password = "password";
    const char *database = "database";
    unsigned int port    = 3306;

    if (mysql_real_connect(con,
                           host,
                           user,
                           password,
                           database,
                           port,
                           NULL,        /* unix_socket */
                           0            /* client_flag */
                           ) == NULL)
    {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    /* ------------------------------------------------------------------ */
    /* 3.  Build and execute the INSERT statement                         */
    /*     (Mirrors the template string from the JS example)              */
    /* ------------------------------------------------------------------ */
    char query[256];
    /* Surround the name with single quotes so that it is treated as text */
    snprintf(query, sizeof(query),
             "INSERT INTO STUDENTS VALUES ('%s', %d)", name, age);

    if (mysql_query(con, query)) {
        fprintf(stderr, "INSERT failed: %s\n", mysql_error(con));
        /* keep behaviour identical to the JS code: just stop on failure */
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    /* ------------------------------------------------------------------ */
    /* 4.  Clean up (close the connection)                                */
    /* ------------------------------------------------------------------ */
    mysql_close(con);
}

/* Simple test driver, not part of the original request */
int main(void)
{
    add_student("Alice", 19);
    return 0;
}