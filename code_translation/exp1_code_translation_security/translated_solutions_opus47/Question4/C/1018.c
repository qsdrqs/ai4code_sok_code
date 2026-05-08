#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>
#include "config.h"  /* Provides DB_HOST, DB_USER, DB_PASSWORD, DB_NAME, DB_PORT */

void add(const char *name, int age, const char *table) {
    MYSQL *connection;
    MYSQL_STMT *stmt;
    MYSQL_BIND bind[2];
    const char *query = "INSERT INTO todos(title, completed) VALUES (?, ?)";
    unsigned long name_length;

    /* Create MySQL connection (equivalent to mysql.createConnection(config)) */
    connection = mysql_init(NULL);
    if (connection == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }

    if (mysql_real_connect(connection, DB_HOST, DB_USER, DB_PASSWORD,
                           DB_NAME, DB_PORT, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(connection));
        mysql_close(connection);
        return;
    }

    /* Prepare the statement */
    stmt = mysql_stmt_init(connection);
    if (stmt == NULL) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        mysql_close(connection);
        return;
    }

    if (mysql_stmt_prepare(stmt, query, strlen(query)) != 0) {
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(connection);
        return;
    }

    /* Bind parameters: [name, age] -> [title, completed] */
    memset(bind, 0, sizeof(bind));

    name_length = strlen(name);
    bind[0].buffer_type   = MYSQL_TYPE_STRING;
    bind[0].buffer        = (char *)name;
    bind[0].buffer_length = name_length;
    bind[0].length        = &name_length;
    bind[0].is_null       = 0;

    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer      = (char *)&age;
    bind[1].is_null     = 0;
    bind[1].length      = 0;

    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(connection);
        return;
    }

    /* Execute query (equivalent to connection.query callback) */
    if (mysql_stmt_execute(stmt) != 0) {
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(connection);
        return;
    }

    /* Print affected rows (equivalent to console.log) */
    printf("Row inserted:%llu\n",
           (unsigned long long)mysql_stmt_affected_rows(stmt));

    /* Cleanup */
    mysql_stmt_close(stmt);
    mysql_close(connection);
}