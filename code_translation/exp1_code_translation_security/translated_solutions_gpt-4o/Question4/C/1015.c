#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void addStudent(const char *name, int age) {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", 
          "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    if (mysql_query(con, query)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("1 record inserted\n");

    mysql_close(con);
}

int main() {
    addStudent("John Doe", 20);
    return 0;
}