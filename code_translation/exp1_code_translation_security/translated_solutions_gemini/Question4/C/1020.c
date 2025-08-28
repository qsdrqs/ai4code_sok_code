#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // MySQL C API header

// Helper function to print errors and exit
void finish_with_error(MYSQL *con) {
  fprintf(stderr, "%s\n", mysql_error(con));
  if (con != NULL) {
    mysql_close(con);
  }
  exit(1);
}

void inserter(const char *name, int age) {
    // --- Connection Details ---
    const char *host = "localhost";
    const char *user = "yourusername";
    const char *password = "yourpassword";
    const char *database = "mydb";
    unsigned int port = 3306; // Default MySQL port

    MYSQL *con = NULL;
    MYSQL_STMT *stmt = NULL;

    // 1. Initialize Connection Handler
    con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    // 2. Connect to the database
    // mysql_real_connect is synchronous, unlike Node.js's async connect
    if (mysql_real_connect(con, host, user, password, database, port, NULL, 0) == NULL) {
        finish_with_error(con);
    }

    printf("Connected!\n");

    // 3. Prepare the SQL statement to prevent SQL injection
    // We use '?' as placeholders for the data
    const char *sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    stmt = mysql_stmt_init(con);
    if (!stmt) {
        finish_with_error(con);
    }

    if (mysql_stmt_prepare(stmt, sql, strlen(sql)) != 0) {
        fprintf(stderr, "mysql_stmt_prepare failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    // 4. Bind parameters to the prepared statement
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind)); // Zero out the structure

    // Bind parameter 1: NAME (string)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char *)name;
    bind[0].buffer_length = strlen(name);

    // Bind parameter 2: AGE (integer)
    bind[1].buffer_type = MYSQL_TYPE_LONG; // For int
    bind[1].buffer = (char *)&age;
    bind[1].is_unsigned = 0;
    bind[1].length = 0;

    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        fprintf(stderr, "mysql_stmt_bind_param failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    // 5. Execute the statement
    if (mysql_stmt_execute(stmt) != 0) {
        fprintf(stderr, "mysql_stmt_execute failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    // Check if the insert was successful
    if (mysql_stmt_affected_rows(stmt) == 1) {
        printf("1 record inserted\n");
    } else {
        printf("Warning: %llu rows affected.\n", mysql_stmt_affected_rows(stmt));
    }

    // 6. Clean up resources
    mysql_stmt_close(stmt);
    mysql_close(con);
}

// Example of how to call the function
int main() {
    // You would get these values from user input or another source
    inserter("John Doe", 25);
    inserter("Jane Smith", 31);
    return 0;
}