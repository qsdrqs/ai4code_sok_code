#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

MYSQL *con;

void insert() {
    // Connect to the database
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }

    char name[256];
    int age;

    // Read name from user
    printf("enter a name: ");
    if (fgets(name, sizeof(name), stdin) == NULL) {
        fprintf(stderr, "Error reading name\n");
        exit(1);
    }
    // Remove trailing newline
    name[strcspn(name, "\n")] = '\0';

    // Read age from user
    printf("enter an age: ");
    if (scanf("%d", &age) != 1) {
        fprintf(stderr, "Invalid age input\n");
        exit(1);
    }

    // Prepare the SQL statement
    MYSQL_STMT *stmt = mysql_stmt_init(con);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        exit(1);
    }

    const char *query = "INSERT INTO students (name, age) VALUES (?, ?)";
    if (mysql_stmt_prepare(stmt, query, strlen(query)) != 0) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        exit(1);
    }

    // Bind parameters
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    // First parameter: name (string)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = name;
    bind[0].buffer_length = strlen(name);
    bind[0].is_null = 0;
    bind[0].length = &bind[0].buffer_length;

    // Second parameter: age (integer)
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = &age;
    bind[1].is_null = 0;
    bind[1].length = NULL;

    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        exit(1);
    }

    // Execute the statement
    if (mysql_stmt_execute(stmt) != 0) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        exit(1);
    } else {
        printf("Number of records inserted: %ld\n", mysql_affected_rows(con));
    }

    // Clean up
    mysql_stmt_close(stmt);
}

int main() {
    // Initialize the MySQL connection
    con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return 1;
    }

    // Call the insert function
    insert();

    // Clean up
    mysql_close(con);
    return 0;
}