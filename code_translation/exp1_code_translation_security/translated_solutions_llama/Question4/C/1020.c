#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// Function to insert a record into the STUDENTS table
void inserter(const char* name, int age) {
    MYSQL* con;
    MYSQL_RES* res;
    MYSQL_ROW row;

    // Initialize MySQL connection
    con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        exit(1);
    }

    // Connect to MySQL database
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }

    printf("Connected!\n");

    // Prepare SQL query
    char sql[256];
    sprintf(sql, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Execute SQL query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }

    printf("1 record inserted\n");

    // Close MySQL connection
    mysql_close(con);
}

int main() {
    // Example usage
    inserter("John Doe", 25);
    return 0;
}