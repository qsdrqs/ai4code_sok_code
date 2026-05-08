#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

void finish_with_error(MYSQL *con) {
    fprintf(stderr, "%s\n", mysql_error(con));
    mysql_close(con);
    exit(1);
}

void finish_with_stmt_error(MYSQL *con, MYSQL_STMT *stmt) {
    fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
    mysql_stmt_close(stmt);
    mysql_close(con);
    exit(1);
}

void insert() {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword",
                           "mydb", 0, NULL, 0) == NULL) {
        finish_with_error(con);
    }

    printf("Connected!\n");

    MYSQL_STMT *stmt = mysql_stmt_init(con);
    if (stmt == NULL) {
        finish_with_error(con);
    }

    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    if (mysql_stmt_prepare(stmt, sql, strlen(sql))) {
        finish_with_stmt_error(con, stmt);
    }

    char name[256];
    int age;

    printf("enter a name: ");
    fgets(name, sizeof(name), stdin);
    name[strcspn(name, "\n")] = '\0';  // strip trailing newline

    printf("enter an age: ");
    scanf("%d", &age);

    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    unsigned long name_length = strlen(name);

    bind[0].buffer_type   = MYSQL_TYPE_STRING;
    bind[0].buffer        = name;
    bind[0].buffer_length = sizeof(name);
    bind[0].length        = &name_length;
    bind[0].is_null       = 0;

    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer      = &age;
    bind[1].is_null     = 0;
    bind[1].length      = 0;

    if (mysql_stmt_bind_param(stmt, bind)) {
        finish_with_stmt_error(con, stmt);
    }

    if (mysql_stmt_execute(stmt)) {
        finish_with_stmt_error(con, stmt);
    }

    printf("Number of records inserted: %llu\n",
           (unsigned long long)mysql_stmt_affected_rows(stmt));

    mysql_stmt_close(stmt);
    mysql_close(con);
}

int main() {
    insert();
    return 0;
}