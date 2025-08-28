#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// Function to insert a record into the students table
void insert(const char* name, int age) {
    // MySQL connection structure
    MYSQL* con;

    // Initialize MySQL library
    mysql_library_init(0, NULL, NULL);

    // Create a new MySQL connection
    con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_library_end();
        return;
    }

    // Connect to MySQL server
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", "STUDENTS", 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        mysql_library_end();
        return;
    }

    printf("Connected!\n");

    // Prepare SQL query
    char sql[256];
    sprintf(sql, "INSERT INTO students (name, age) VALUES ('%s', %d)", name, age);

    // Execute SQL query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "%s\n", mysql_error(con));
    } else {
        printf("1 record inserted\n");
    }

    // Close MySQL connection
    mysql_close(con);

    // End MySQL library
    mysql_library_end();
}

int main() {
    // Example usage
    insert("John Doe", 20);
    return 0;
}