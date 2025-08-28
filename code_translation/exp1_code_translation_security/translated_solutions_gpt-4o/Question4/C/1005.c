#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

void add_student(const char *name, int age) {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    // Replace "host", "user", "password", and "database" with your actual database credentials
    if (mysql_real_connect(con, "host", "user", "password", "database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS VALUES ('%s', %d)", name, age);

    if (mysql_query(con, query)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(con));
    }

    mysql_close(con);
}

int main() {
    add_student("John Doe", 20);
    return 0;
}