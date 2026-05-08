#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

void addStudent(const char *name, int age) {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword",
                           "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }

    printf("Connected!\n");

    char sql[512];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')",
             name, age);

    if (mysql_query(con, sql)) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }

    printf("1 record inserted\n");

    mysql_close(con);
}