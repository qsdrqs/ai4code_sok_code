#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// Function to add a student to the database
void add_student(const char* name, int age) {
    // Initialize MySQL connection structure
    MYSQL* con;
    con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        exit(1);
    }

    // Establish a connection to the database
    // Replace with your actual database credentials
    if (!mysql_real_connect(con, "your_host", "your_user", "your_password", "your_database", 0, NULL, 0)) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }

    // Prepare the SQL query
    char query[256];
    sprintf(query, "INSERT INTO STUDENTS VALUES ('%s', %d)", name, age);

    // Execute the SQL query
    if (mysql_query(con, query)) {
        fprintf(stderr, "%s\n", mysql_error(con));
    }

    // Close the MySQL connection
    mysql_close(con);
}

int main() {
    // Example usage
    add_student("John Doe", 20);
    return 0;
}