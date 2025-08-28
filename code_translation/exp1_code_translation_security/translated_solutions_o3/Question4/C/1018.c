/*
 * add.c  (translation of the JavaScript function “add”)
 *
 * Build example (assuming the MySQL client library and includes are present):
 *     gcc -Wall -O2 -o add add.c -lmysqlclient
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

/* --------------------------------------------------------------------------
 * External “config” objects — supplied elsewhere in the project.
 * -------------------------------------------------------------------------- */
#include "config.h"        /* exposes: db_config get_db_config(void); */

void add(const char *name, int age, const char *table /* unused – kept for API parity */)
{
    /* ---- 1. Connect to MySQL -------------------------------------------- */
    db_config cfg = get_db_config();           /* provided by config.h        */

    MYSQL *conn = mysql_init(NULL);
    if (conn == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }

    if (mysql_real_connect(conn,
                           cfg.host,
                           cfg.user,
                           cfg.password,
                           cfg.database,
                           cfg.port,
                           NULL,
                           0) == NULL)
    {
        fprintf(stderr, "mysql_real_connect() failed: %s\n", mysql_error(conn));
        mysql_close(conn);
        return;
    }

    /* ---- 2. Prepare the INSERT statement -------------------------------- */
    const char *sql =
        "INSERT INTO todos(title, completed) VALUES(?, ?)";

    MYSQL_STMT *stmt = mysql_stmt_init(conn);
    if (stmt == NULL) {
        fprintf(stderr, "mysql_stmt_init() failed: %s\n", mysql_error(conn));
        mysql_close(conn);
        return;
    }

    if (mysql_stmt_prepare(stmt, sql, (unsigned long)strlen(sql))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n",
                mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(conn);
        return;
    }

    /* ---- 3. Bind parameters --------------------------------------------- */
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    /* param 0: title (name) – string */
    unsigned long name_len = (unsigned long)strlen(name);
    bind[0].buffer_type   = MYSQL_TYPE_STRING;
    bind[0].buffer        = (void *)name;
    bind[0].buffer_length = name_len;
    bind[0].length        = &name_len;
    bind[0].is_null       = 0;

    /* param 1: completed (age) – integer */
    int completed = age;
    bind[1].buffer_type   = MYSQL_TYPE_LONG;
    bind[1].buffer        = (void *)&completed;
    bind[1].is_null       = 0;
    bind[1].length        = 0;

    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n",
                mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(conn);
        return;
    }

    /* ---- 4. Execute & report -------------------------------------------- */
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n",
                mysql_stmt_error(stmt));
    } else {
        my_ulonglong affected = mysql_stmt_affected_rows(stmt);
        printf("Row inserted: %llu\n", affected);
    }

    /* ---- 5. Cleanup ------------------------------------------------------ */
    mysql_stmt_close(stmt);
    mysql_close(conn);
}