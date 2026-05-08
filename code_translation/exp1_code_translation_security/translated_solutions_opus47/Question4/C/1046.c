#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

// #include "config.h"
// Create config and connection to your respective database then used the function
// MYSQL *connection = mysql_init(NULL);
// mysql_real_connect(connection, host, user, password, database, 0, NULL, 0);

MYSQL *connection;

void insertInto(const char *name, int age)
{
    char sql[256];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS(NAME,AGE) VALUES ('%s', %d)",
             name, age);

    mysql_query(connection, sql);
    mysql_close(connection);
}