#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

void inserter(const char *name, int age) {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    // Equivalent to createConnection + connect
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword",
                           "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    // Build the SQL query with the provided parameters
    char sql[512];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);

    // Equivalent to con.query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "Query error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("1 record inserted\n");

    mysql_close(con);
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <name> <age>\n", argv[0]);
        return EXIT_FAILURE;
    }

    inserter(argv[1], atoi(argv[2]));
    return EXIT_SUCCESS;
}