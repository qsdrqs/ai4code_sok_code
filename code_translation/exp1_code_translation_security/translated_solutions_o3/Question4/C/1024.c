/*
 *  customers_insert.c
 *
 *  Rough 1-to-1 translation of the given Node.js example.
 *  Connects to a MySQL server, inserts one record, prints the
 *  number of affected rows, then exits.
 *
 *  NOT TESTED!!
 */

#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>   /* libmysqlclient header */

/* Convenience helper – print error, clean up, and die. */
static void finish_with_error(MYSQL *con)
{
    fprintf(stderr, "%s\n", mysql_error(con));
    mysql_close(con);
    exit(EXIT_FAILURE);
}

int main(void)
{
    /* Initialize connection handle */
    MYSQL *con = mysql_init(NULL);
    if (con == NULL)
    {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    /* Connect to the database (host, user, pwd, db, port, unix_socket, flags) */
    if (mysql_real_connect(con,
                           "127.0.0.1",     /* host   */
                           "yourusername",  /* user   */
                           "yourpassword",  /* passwd */
                           "mydb",          /* db     */
                           0,               /* default port */
                           NULL,            /* default unix socket */
                           0)               /* no special flags   */
        == NULL)
    {
        finish_with_error(con);
    }

    printf("Connected!\n");

    /* The SQL statement – single row, matching the JS example */
    const char *sql =
        "INSERT INTO customers (name, address) VALUES ('XXXXX', 'XXXXX')";

    /* Execute the query */
    if (mysql_query(con, sql))
    {
        finish_with_error(con);
    }

    /* Obtain and print number of affected rows */
    printf("Number of records inserted: %llu\n",
           (unsigned long long)mysql_affected_rows(con));

    /* Clean up */
    mysql_close(con);
    return EXIT_SUCCESS;
}