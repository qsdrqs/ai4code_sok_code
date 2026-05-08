#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

/* Global connection handle (equivalent to `var con = mysql.createConnection({...})`) */
MYSQL *con;

void add_student(const char *name, int age) {
    /* Equivalent to `con.connect(function(err) { if (err) throw err; ... })` */
    if (mysql_real_connect(con, "host", "user", "password", "database",
                           0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        exit(EXIT_FAILURE);   /* throw err */
    }

    /* Equivalent to `con.query(`INSERT INTO STUDENTS VALUES (${name}, ${age})`, (x,y,z)=>{})` */
    char query[512];
    snprintf(query, sizeof(query),
             "INSERT INTO STUDENTS VALUES (%s, %d)", name, age);
    mysql_query(con, query);   /* the empty callback (x,y,z)=>{} means we ignore the result */
}

int main(void) {
    /* Equivalent to `mysql.createConnection({...})` */
    con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return EXIT_FAILURE;
    }

    /* add_student("John", 20); */

    mysql_close(con);
    return 0;
}