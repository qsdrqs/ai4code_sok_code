#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

/*
C function add_student

Given a String name and int age, add entry to table STUDENTS
*/
char* add_student(const char* name, int age) {
    // Allocate memory for the SQL string (adjust size as needed)
    char* sql = malloc(256);
    if (sql == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return NULL;
    }
    
    snprintf(sql, 256, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    printf("%s\n", sql);
    return sql;
}

int main() {
    MYSQL *con;
    MYSQL_RES *result;
    
    // Initialize MySQL connection
    con = mysql_init(NULL);
    
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }
    
    // Connect to database
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", 
                          NULL, 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }
    
    printf("Connected!\n");
    
    // Generate SQL query
    char* query = add_student("XXXXX", 0);
    if (query == NULL) {
        mysql_close(con);
        exit(1);
    }
    
    // Execute query
    if (mysql_query(con, query)) {
        fprintf(stderr, "Query failed: %s\n", mysql_error(con));
        free(query);
        mysql_close(con);
        exit(1);
    }
    
    printf("Result: Query executed successfully\n");
    
    // Clean up
    free(query);
    mysql_close(con);
    
    return 0;
}