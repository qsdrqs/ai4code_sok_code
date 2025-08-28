/*
 * Translation of the given JavaScript snippet to C.
 * All symbols such as DB_HOST, DB_USER, DB_PASS, DB_NAME and DB_PORT
 * are assumed to be supplied elsewhere (e.g., via a supplied header or
 * compile-time definitions), as per the problem statement.
 *
 * Build example (assuming the MySQL client library is installed):
 *   gcc -o insert_student insert_student.c -lmysqlclient
 */

#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

/* -------------------------------------------------------------------------- */
/* insertStudent : reproduces the behaviour of the JS version                 */
/* -------------------------------------------------------------------------- */
int insertStudent(const char *name, int age)
{
    /* 1. Allocate a connection handle */
    MYSQL *conn = mysql_init(NULL);
    if (conn == NULL)
    {
        fprintf(stderr, "mysql_init() failed\n");
        return -1;
    }

    /* 2. Establish the connection (details supplied elsewhere) */
    if (mysql_real_connect(conn,
                           DB_HOST,   /* host */
                           DB_USER,   /* user */
                           DB_PASS,   /* password */
                           DB_NAME,   /* database */
                           DB_PORT,   /* port */
                           NULL,      /* unix socket */
                           0)         /* flags */
        == NULL)
    {
        fprintf(stderr, "mysql_real_connect() failed: %s\n",
                mysql_error(conn));
        mysql_close(conn);
        return -1;
    }

    /* 3. Compose the SQL string
          (kept identical to the JavaScript version on purpose)          */
    const char *sql =
        "INSERT INTO STUDENTS(title, completed) "
        "VALUES('Learn how to insert a new row', true)";

    /* 4. Execute the statement */
    if (mysql_query(conn, sql))
    {
        fprintf(stderr, "INSERT failed: %s\n", mysql_error(conn));
        mysql_close(conn);
        return -1;
    }

    /* 5. Close the connection */
    mysql_close(conn);

    return 0; /* success */
}

/* -------------------------------------------------------------------------- */
/* Demo entry point (mimics the final JS call)                                */
/* -------------------------------------------------------------------------- */
int main(void)
{
    if (insertStudent("XXXXX", 0) != 0)
    {
        fprintf(stderr, "insertStudent() failed\n");
        return EXIT_FAILURE;
    }
    return EXIT_SUCCESS;
}