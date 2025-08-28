#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

void createEntry(const char *name, int age) {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    if (mysql_real_connect(con, "host", "user", "password", "database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS (name, age) VALUES ('%s', %d)", name, age);

    if (mysql_query(con, query)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    mysql_close(con);
}

int main() {
    createEntry("John Doe", 20);
    return 0;
}