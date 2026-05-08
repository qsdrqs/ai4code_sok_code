#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

extern MYSQL *con;  /* Global MySQL connection handle (provided as dependency) */

void createEntry(const char *name, int age) {
    /* Equivalent to con.connect(...) */
    if (mysql_real_connect(con, "localhost", "user", "password",
                           "database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        exit(EXIT_FAILURE);  /* equivalent to 'throw err' */
    }

    /* Initialize a prepared statement (needed for parameter binding like '?') */
    MYSQL_STMT *stmt = mysql_stmt_init(con);
    if (stmt == NULL) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        exit(EXIT_FAILURE);
    }

    /* Prepare the INSERT query */
    const char *query = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
    if (mysql_stmt_prepare(stmt, query, strlen(query)) != 0) {
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        exit(EXIT_FAILURE);
    }

    /* Bind the parameters [name, age] */
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    unsigned long name_length = strlen(name);
    bind[0].buffer_type   = MYSQL_TYPE_STRING;
    bind[0].buffer        = (void *)name;
    bind[0].buffer_length = name_length;
    bind[0].length        = &name_length;

    bind[1].buffer_type   = MYSQL_TYPE_LONG;
    bind[1].buffer        = (void *)&age;

    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        exit(EXIT_FAILURE);
    }

    /* Execute the statement (equivalent to con.query callback) */
    if (mysql_stmt_execute(stmt) != 0) {
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        exit(EXIT_FAILURE);
    }

    mysql_stmt_close(stmt);
}