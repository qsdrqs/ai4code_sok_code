/*
   C program: add_student.c
   Equivalent to the original JavaScript sample.

   NOTE:
   –  Uses the MySQL C API (mysqlclient).
   –  Error handling is kept minimal, matching the JS example’s
      “throw err” behaviour.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

/* ------------------------------------------------------------
   Helper that mimics the JS function `add_student`
   Returns a heap-allocated SQL string – caller must free().
   ------------------------------------------------------------ */
char *add_student(const char *name, int age)
{
    /* Enough space: name length + fixed overhead               */
    size_t buf_len = strlen(name) + 64;   /* plenty for our query */
    char  *sql     = (char *)malloc(buf_len);

    if (sql == NULL) {
        fprintf(stderr, "Out of memory!\n");
        exit(EXIT_FAILURE);
    }

    /* Compose INSERT statement */
    snprintf(sql, buf_len,
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);

    /* Console output (console.log equivalent) */
    puts(sql);

    return sql;            /* caller frees */
}

/* ------------------------------------------------------------
   Main: connect, call add_student, issue query, print result
   ------------------------------------------------------------ */
int main(void)
{
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return EXIT_FAILURE;
    }

    /* Open connection (no default DB supplied, like original JS) */
    if (mysql_real_connect(con,
                           "localhost",      /* host       */
                           "yourusername",   /* user       */
                           "yourpassword",   /* password   */
                           NULL,             /* database   */
                           0,                /* port (0=default) */
                           NULL,             /* unix socket */
                           0) == NULL)       /* flags       */
    {
        fprintf(stderr, "Connection error: %s\n", mysql_error(con));
        mysql_close(con);
        return EXIT_FAILURE;
    }

    puts("Connected!");

    /* Build the SQL string exactly once (JS did: add_student("XXXXX","0")) */
    char *sql = add_student("XXXXX", 0);

    /* Execute query */
    if (mysql_query(con, sql))
    {
        fprintf(stderr, "Query error: %s\n", mysql_error(con));
        free(sql);
        mysql_close(con);
        return EXIT_FAILURE;
    }

    /* Report result (rows affected, close enough to JS “result”) */
    printf("Result: %llu rows affected\n",
           (unsigned long long)mysql_affected_rows(con));

    /* Clean-up */
    free(sql);
    mysql_close(con);

    return EXIT_SUCCESS;
}