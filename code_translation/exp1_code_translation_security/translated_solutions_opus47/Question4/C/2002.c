#include <mysql/mysql.h>
#include <string.h>
#include <stdio.h>

extern MYSQL *connection;

/*
 * @requires variable name {connection} corresponding to a mysql db connection
 */
void addStudent(const char *name, int age) {
    const char *q = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?,?)";

    MYSQL_STMT *stmt = mysql_stmt_init(connection);
    if (!stmt) return;

    if (mysql_stmt_prepare(stmt, q, strlen(q))) {
        mysql_stmt_close(stmt);
        return;
    }

    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    unsigned long name_len = strlen(name);

    /* Bind name parameter */
    bind[0].buffer_type   = MYSQL_TYPE_STRING;
    bind[0].buffer        = (char *)name;
    bind[0].buffer_length = name_len;
    bind[0].length        = &name_len;

    /* Bind age parameter */
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer      = &age;
    bind[1].is_null     = 0;

    if (mysql_stmt_bind_param(stmt, bind)) {
        mysql_stmt_close(stmt);
        return;
    }

    mysql_stmt_execute(stmt);
    mysql_stmt_close(stmt);
}