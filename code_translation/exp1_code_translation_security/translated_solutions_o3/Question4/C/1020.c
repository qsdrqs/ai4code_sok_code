#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>   /* MySQL client library header */

/*
 * Inserts a single record into the STUDENTS table.
 *
 * Parameters
 * ----------
 *  name : const char*
 *      Student’s name  (null–terminated C-string).
 *  age  : int
 *      Student’s age.
 *
 * Notes
 * -----
 *  – All connection credentials are hard-coded to stay faithful to
 *    the original JavaScript snippet, but of course in real programs
 *    you would externalize them.
 *  – A very small amount of error handling is added so that, like
 *    Node.js’s “throw err”, the program aborts immediately when
 *    something goes wrong.
 */
void inserter(const char *name, int age)
{
    /* -------- Connection information (mirrors JS example) -------- */
    const char *host     = "localhost";
    const char *user     = "yourusername";
    const char *password = "yourpassword";
    const char *database = "mydb";

    /* -------- Initialize and connect -------- */
    MYSQL *con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    if (mysql_real_connect(con, host, user, password, database,
                           0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    /* -------- Build and execute INSERT statement -------- */
    char query[512];
    /* Same SQL as the JS version, but with formatted values.   */
    snprintf(query, sizeof(query),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);

    if (mysql_query(con, query)) {
        fprintf(stderr, "INSERT failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("1 record inserted\n");

    /* -------- Clean up -------- */
    mysql_close(con);
}