#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>

int main() {
    // Initialize MySQL connection
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    // Connect to the database
    if (mysql_real_connect(con, "127.0.0.1", "yourusername", "yourpassword", "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        fprintf(stderr, "Error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    // SQL query to insert data
    const char *sql = "INSERT INTO customers (name, address) VALUES ('XXXXX', 'XXXXX')";

    // Execute the query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Get the number of affected rows
    printf("Number of records inserted: %lld\n", mysql_affected_rows(con));

    // Close the connection
    mysql_close(con);

    return 0;
}