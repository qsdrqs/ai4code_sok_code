#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

// Global MySQL connection
MYSQL *con;

// Initialize MySQL connection (equivalent to createConnection)
void init_connection() {
    con = mysql_init(NULL);
    
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }
    
    // Connect to database - replace with your actual connection parameters
    if (mysql_real_connect(con, "localhost", "username", "password", 
                          "database_name", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(con);
        exit(1);
    }
}

void add_student(const char* name, int age) {
    // Check if connection is established
    if (con == NULL) {
        fprintf(stderr, "Database connection not established\n");
        return;
    }
    
    // Create the SQL query string
    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS VALUES ('%s', %d)", name, age);
    
    // Execute the query
    if (mysql_query(con, query)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(con));
        return;
    }
    
    printf("Student added successfully\n");
}

// Clean up function to close connection
void cleanup_connection() {
    if (con != NULL) {
        mysql_close(con);
    }
}

// Example usage
int main() {
    init_connection();
    
    add_student("John Doe", 20);
    add_student("Jane Smith", 22);
    
    cleanup_connection();
    return 0;
}