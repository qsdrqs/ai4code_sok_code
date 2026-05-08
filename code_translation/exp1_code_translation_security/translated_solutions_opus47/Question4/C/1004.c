#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

/*
C function add_student

Given a string name and int age, add entry to table STUDENTS
*/
char* add_student(const char* name, int age) {
    static char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    printf("%s\n", sql);
    return sql;
}

int main(void) {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword",
                           NULL, 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }

    printf("Connected!\n");

    if (mysql_query(con, add_student("XXXXX", 0))) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }

    printf("Result: %llu\n", (unsigned long long)mysql_affected_rows(con));

    mysql_close(con);
    return 0;
}