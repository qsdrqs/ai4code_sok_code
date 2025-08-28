#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// Function to insert a record into the STUDENTS table
void insertion(const char* name, int age) {
    MYSQL* con;
    MYSQL_RES* res;
    MYSQL_ROW row;

    // Initialize MySQL connection
    con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        exit(1);
    }

    // Connect to MySQL server
    if (mysql_real_connect(con, "localhost", "your_username", "your_password", "your_database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }

    // Prepare SQL query
    char query[256];
    sprintf(query, "INSERT INTO STUDENTS (NAME,AGE) VALUES ('%s', %d)", name, age);

    // Execute SQL query
    if (mysql_query(con, query)) {
        fprintf(stderr, "%s\n", mysql_error(con));
    } else {
        printf("Record inserted successfully\n");
    }

    // Close MySQL connection
    mysql_close(con);
}

int main() {
    // Example usage
    insertion("John Doe", 25);
    return 0;
}