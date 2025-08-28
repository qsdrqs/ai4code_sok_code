/*  add.c
 *
 *  C translation of the JavaScript “add” function shown in the prompt.
 *  Assumes that a live MySQL connection called  `connection`
 *  is available elsewhere in the program.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

/* This variable must be defined and opened (mysql_real_connect) elsewhere. */
extern MYSQL *connection;

/* ---------------------------------------------------------------------- */
/*  add – inserts one (name, age) row into the students table             */
/* ---------------------------------------------------------------------- */
void add(const char *name, unsigned int age)
{
    const char *query = "INSERT INTO students (name, age) VALUES (?, ?)";

    /* -------------------------------------------------------------- */
    /*  Create and prepare a statement                                */
    /* -------------------------------------------------------------- */
    MYSQL_STMT *stmt = mysql_stmt_init(connection);
    if (stmt == NULL) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        exit(EXIT_FAILURE);
    }

    if (mysql_stmt_prepare(stmt, query, (unsigned long)strlen(query))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n",
                mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        exit(EXIT_FAILURE);
    }

    /* -------------------------------------------------------------- */
    /*  Bind parameters                                               */
    /* -------------------------------------------------------------- */
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    /* 1st parameter – name (string) */
    unsigned long name_len = strlen(name);
    bind[0].buffer_type   = MYSQL_TYPE_STRING;
    bind[0].buffer        = (void *)name;   /* cast away const for API */
    bind[0].buffer_length = name_len;
    bind[0].length        = &name_len;
    bind[0].is_null       = 0;

    /* 2nd parameter – age (integer) */
    bind[1].buffer_type   = MYSQL_TYPE_LONG;
    bind[1].buffer        = (void *)&age;
    bind[1].is_null       = 0;

    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n",
                mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        exit(EXIT_FAILURE);
    }

    /* -------------------------------------------------------------- */
    /*  Execute statement                                             */
    /* -------------------------------------------------------------- */
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n",
                mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        exit(EXIT_FAILURE);
    }

    printf("Added %s to students table\n", name);

    mysql_stmt_close(stmt);
}