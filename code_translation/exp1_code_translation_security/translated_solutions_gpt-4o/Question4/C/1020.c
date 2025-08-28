#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>

void inserter(const char *name, int age) {
    // Initialize MySQL connection
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    // Connect to the database
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        fprintf(stderr, "Error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    // Prepare the SQL query
    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Execute the query
    if (mysql_query(con, query)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("1 record inserted\n");

    // Close the connection
    mysql_close(con);
}

int main() {
    // Example usage of the inserter function
    inserter("John Doe", 25);
    return 0;
}