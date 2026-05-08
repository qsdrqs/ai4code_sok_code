#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

void insert(const char *name, int age) {
    MYSQL *con = mysql_init(NULL);
    
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }
    
    /* Equivalent to mysql.createConnection({...}) and con.connect() */
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword",
                           "STUDENTS", 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }
    
    printf("Connected!\n");
    
    /* Escape the name string to prevent SQL injection */
    char escaped_name[512];
    mysql_real_escape_string(con, escaped_name, name, strlen(name));
    
    /* Build the SQL query */
    char sql[1024];
    snprintf(sql, sizeof(sql),
             "INSERT INTO students (name, age) VALUES ('%s', %d)",
             escaped_name, age);
    
    /* Equivalent to con.query(sql, ...) */
    if (mysql_query(con, sql)) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }
    
    printf("1 record inserted\n");
    
    mysql_close(con);
}

int main(void) {
    insert("John Doe", 25);
    return 0;
}