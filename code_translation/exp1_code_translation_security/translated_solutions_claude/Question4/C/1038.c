#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

void insertion(const char* name, int age) {
    MYSQL *con = mysql_init(NULL);
    
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }
    
    // Connect to database (you'll need to provide actual connection parameters)
    if (mysql_real_connect(con, "localhost", "username", "password", 
                          "database_name", 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        return;
    }
    
    // Prepare SQL statement with proper formatting
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    
    // Execute the query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "INSERT failed: %s\n", mysql_error(con));
    } else {
        printf("Record inserted successfully\n");
    }
    
    mysql_close(con);
}

// Example usage
int main() {
    insertion("John Doe", 20);
    return 0;
}