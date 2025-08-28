#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

void addStudent(const char* name, int age) {
    MYSQL *con;
    char sql[512];
    
    // Initialize MySQL connection
    con = mysql_init(NULL);
    
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }
    
    // Connect to database
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", 
                          "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }
    
    printf("Connected!\n");
    
    // Prepare SQL statement
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')", 
             name, age);
    
    // Execute query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "Query failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }
    
    printf("1 record inserted\n");
    
    // Close connection
    mysql_close(con);
}

// Example usage
int main() {
    addStudent("John Doe", 20);
    return 0;
}