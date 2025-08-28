#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// MySQL connection settings
#define MYSQL_HOST "127.0.0.1"
#define MYSQL_USER "yourusername"
#define MYSQL_PASSWORD "yourpassword"
#define MYSQL_DATABASE "mydb"

// SQL query to insert a record into the customers table
#define INSERT_QUERY "INSERT INTO customers (name, address) VALUES (%s, %s)"

int main() {
    MYSQL *conn;
    MYSQL_RES *res;
    MYSQL_ROW row;

    // Initialize MySQL library
    mysql_library_init(0, NULL, NULL);

    // Create a new MySQL connection
    conn = mysql_init(NULL);
    if (conn == NULL) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        mysql_library_end();
        exit(1);
    }

    // Connect to the MySQL database
    if (mysql_real_connect(conn, MYSQL_HOST, MYSQL_USER, MYSQL_PASSWORD, MYSQL_DATABASE, 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        mysql_close(conn);
        mysql_library_end();
        exit(1);
    }

    printf("Connected!\n");

    // Prepare data to be inserted
    char *name = "XXXXX";
    char *address = "XXXXX";

    // Execute the INSERT query
    char query[256];
    sprintf(query, INSERT_QUERY, name, address);
    int result = mysql_query(conn, query);
    if (result != 0) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        mysql_close(conn);
        mysql_library_end();
        exit(1);
    }

    // Get the number of affected rows
    my_ulonglong affected_rows = mysql_affected_rows(conn);
    printf("Number of records inserted: %lu\n", affected_rows);

    // Close the MySQL connection
    mysql_close(conn);

    // End the MySQL library
    mysql_library_end();

    return 0;
}