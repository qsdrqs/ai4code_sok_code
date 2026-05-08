#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

void insertion(const char *name, int age) {
    MYSQL *con = mysql_init(NULL);
    
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }
    
    /* Equivalent of mysql.createConnection() + con.connect() */
    if (mysql_real_connect(con, 
                           "localhost",       /* host */
                           "username",        /* user */
                           "password",        /* password */
                           "database_name",   /* db name */
                           0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection error: %s\n", mysql_error(con));
        mysql_close(con);
        return;
    }
    
    /* Build the SQL query string */
    char sql[512];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);
    
    /* Execute the query */
    if (mysql_query(con, sql)) {
        fprintf(stderr, "Insert error: %s\n", mysql_error(con));
        mysql_close(con);
        return;
    }
    
    printf("Record inserted successfully\n");
    mysql_close(con);
}

int main(void) {
    insertion("Alice", 21);
    return 0;
}