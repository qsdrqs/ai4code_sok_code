#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

void insertion(const char *name, int age) {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }

    // Connect to the database
    if (mysql_real_connect(con, "host", "user", "password", "database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(con);
        return;
    }

    // Prepare the SQL query
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES('%s', %d)", name, age);

    // Execute the SQL query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(con));
    }

    // Close the connection
    mysql_close(con);
}

int main() {
    // Example usage
    insertion("John Doe", 25);
    return 0;
}