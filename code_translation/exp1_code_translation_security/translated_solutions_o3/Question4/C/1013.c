/*
 * Compile (Linux):
 *   gcc -o insert_student insert_student.c -lmysqlclient
 *
 * NOTE:
 *   – Make sure the MySQL client headers / libs are installed
 *     (Debian/Ubuntu: apt-get install libmysqlclient-dev)
 *   – Adjust HOST, USER, PASS and DB constants to your environment.
 */

#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ---- connection parameters ------------------------------------------- */
#define HOST "localhost"
#define USER "yourusername"
#define PASS "yourpassword"
#define DB   "mydb"
/* ---------------------------------------------------------------------- */

static void die(MYSQL *con, const char *msg)
{
    fprintf(stderr, "%s: %s\n", msg, mysql_error(con));
    if (con) mysql_close(con);
    exit(EXIT_FAILURE);
}

/* ---------------------------------------------------------------------- */
void insert(void)
{
    /* 1. initialise handle ------------------------------------------------*/
    MYSQL *con = mysql_init(NULL);
    if (!con)
    {
        fprintf(stderr, "mysql_init() failed (out of memory?)\n");
        exit(EXIT_FAILURE);
    }

    /* 2. connect ----------------------------------------------------------*/
    if (!mysql_real_connect(con, HOST, USER, PASS, DB, 0, NULL, 0))
        die(con, "mysql_real_connect()");

    puts("Connected!");

    /* 3. read user input --------------------------------------------------*/
    char name[256];
    int  age;

    printf("enter a name: ");
    if (!fgets(name, sizeof(name), stdin))
        die(con, "stdin");

    /* strip trailing '\n' inserted by fgets */
    name[strcspn(name, "\n")] = '\0';

    printf("enter an age: ");
    if (scanf("%d", &age) != 1)
    {
        fputs("invalid age\n", stderr);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }
    /* eat the newline left by scanf */
    getchar();

    /* 4. prepare & execute statement -------------------------------------*/
    const char *stmt_sql = "INSERT INTO students (name, age) VALUES(?, ?)";

    MYSQL_STMT *stmt = mysql_stmt_init(con);
    if (!stmt) die(con, "mysql_stmt_init()");

    if (mysql_stmt_prepare(stmt, stmt_sql, (unsigned long)strlen(stmt_sql)))
        die(con, "mysql_stmt_prepare()");

    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    /* param 0: name (string) */
    bind[0].buffer_type   = MYSQL_TYPE_STRING;
    bind[0].buffer        = (char *)name;
    bind[0].buffer_length = strlen(name);

    /* param 1: age (signed int) */
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer      = (char *)&age;
    bind[1].is_unsigned = 0;

    if (mysql_stmt_bind_param(stmt, bind))
        die(con, "mysql_stmt_bind_param()");

    if (mysql_stmt_execute(stmt))
        die(con, "mysql_stmt_execute()");

    printf("Number of records inserted: %llu\n",
           (unsigned long long)mysql_stmt_affected_rows(stmt));

    /* 5. cleanup ----------------------------------------------------------*/
    mysql_stmt_close(stmt);
    mysql_close(con);
}

/* ---------------------------------------------------------------------- */
int main(void)
{
    insert();
    return 0;
}