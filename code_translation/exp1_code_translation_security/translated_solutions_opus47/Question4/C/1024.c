/*
Is not TESTED!!
Compile with: gcc -o program program.c $(mysql_config --cflags --libs)
Requires: libmysqlclient-dev (Debian/Ubuntu) or mysql-devel (RHEL/CentOS)
*/
#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>

int main(void) {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    /* connect to your database */
    if (mysql_real_connect(con,
                           "127.0.0.1",       /* host     */
                           "yourusername",    /* user     */
                           "yourpassword",    /* password */
                           "mydb",            /* database */
                           0,                 /* port (0 = default) */
                           NULL,              /* unix socket */
                           0) == NULL) {      /* client flag */
        fprintf(stderr, "Connection error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    /*
     * Equivalent of:
     *   var sql = "INSERT INTO customers (name, address) VALUES ?";
     *   var values = [ ['XXXXX', 'XXXXX'] ];
     *   con.query(sql, [values], ...);
     *
     * In plain C with libmysqlclient we build the full INSERT statement.
     * Add more (val1, val2) tuples separated by commas to insert multiple rows.
     */
    const char *sql =
        "INSERT INTO customers (name, address) VALUES "
        "('XXXXX', 'XXXXX')";

    if (mysql_query(con, sql)) {
        fprintf(stderr, "Query error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    my_ulonglong affected_rows = mysql_affected_rows(con);
    printf("Number of records inserted: %llu\n",
           (unsigned long long) affected_rows);

    mysql_close(con);
    return EXIT_SUCCESS;
}