/* insert.c */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql.h>          /* supplied externally */

void insert(const char *name, int age)
{
    /* 1. Create / initialise a connection handle */
    MYSQL *con = mysql_init(NULL);
    if (con == NULL)
    {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }

    /* 2. Connect to MySQL */
    if (mysql_real_connect(con,
                           "localhost",         /* host     */
                           "yourusername",      /* user     */
                           "yourpassword",      /* password */
                           "STUDENTS",          /* database */
                           0,                   /* port (0 = default) */
                           NULL,                /* UNIX socket / path */
                           0)                   /* flags    */
        == NULL)
    {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        return;
    }

    printf("Connected!\n");

    /* 3. Compose and run the INSERT statement               */
    /* (For production code prefer a prepared statement.)    */
    char query[512];
    snprintf(query, sizeof(query),
             "INSERT INTO students (name, age) VALUES ('%s', %d)",
             name, age);

    if (mysql_query(con, query))
    {
        fprintf(stderr, "INSERT failed: %s\n", mysql_error(con));
        mysql_close(con);
        return;
    }

    printf("1 record inserted\n");

    /* 4. Clean up */
    mysql_close(con);
}

/* Optional test harness ----------------------------------- */
/*
int main(void)
{
    insert("Alice", 21);
    return 0;
}
*/