#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// Function to add a student to the database
void addStudent(const char* name, int age) {
    // MySQL connection settings
    const char* host = "localhost";
    const char* user = "yourusername";
    const char* password = "yourpassword";
    const char* database = "mydb";

    // Create a MySQL connection object
    MYSQL* con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        return;
    }

    // Connect to the MySQL database
    if (mysql_real_connect(con, host, user, password, database, 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        return;
    }

    printf("Connected!\n");

    // Prepare the SQL query
    char sql[256];
    sprintf(sql, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')", name, age);

    // Execute the SQL query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "%s\n", mysql_error(con));
    } else {
        printf("1 record inserted\n");
    }

    // Close the MySQL connection
    mysql_close(con);
}

int main() {
    // Example usage
    addStudent("John Doe", 20);
    return 0;
}