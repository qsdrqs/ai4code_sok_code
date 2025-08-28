/*
 * C translation of the provided JavaScript MySQL insertion code.
 * This version uses the MySQL C Connector library with prepared statements
 * for security and efficiency.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // The main MySQL C API header

// A helper function to print the last MySQL error and exit the program.
void finish_with_error(MYSQL *con) {
  fprintf(stderr, "MySQL Error: %s\n", mysql_error(con));
  if (con != NULL) {
    mysql_close(con);
  }
  exit(EXIT_FAILURE);
}

int main(void) {
    // --- 1. Connection Details (equivalent to the JS config object) ---
    const char *host = "127.0.0.1";
    const char *user = "yourusername";
    const char *password = "yourpassword";
    const char *database = "mydb";
    unsigned int port = 3306; // Default MySQL port

    // --- Data to be inserted (equivalent to the JS 'values' array) ---
    // The JavaScript `VALUES ?` with an array of arrays is a bulk insert.
    // In C, we typically loop through the data and execute a prepared statement for each row.
    const char *values[][2] = {
        {"Customer A", "Address A"},
        {"Customer B", "Address B"},
        {"Customer C", "Address C"}
        // Add more rows here as needed
    };
    int num_rows = sizeof(values) / sizeof(values[0]);

    MYSQL *con = NULL;
    MYSQL_STMT *stmt = NULL;
    MYSQL_BIND bind[2]; // We have two parameters (?, ?) in our SQL query

    long long total_affected_rows = 0;

    // --- 2. Initialize and Connect to the Database ---
    con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    // mysql_real_connect is the equivalent of `con.connect()`
    if (mysql_real_connect(con, host, user, password, database, port, NULL, 0) == NULL) {
        finish_with_error(con);
    }

    printf("Connected to database!\n");

    // --- 3. Prepare the INSERT Statement ---
    // The '?' are placeholders for the data we will bind later.
    const char *sql = "INSERT INTO customers (name, address) VALUES (?, ?)";

    stmt = mysql_stmt_init(con);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    if (mysql_stmt_prepare(stmt, sql, strlen(sql)) != 0) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // --- 4. Bind Parameters and Execute in a Loop ---
    // Prepare the bind structure. We will reuse it for each row.
    memset(bind, 0, sizeof(bind));

    char data_name[128];
    char data_address[256];
    unsigned long name_length;
    unsigned long address_length;

    // Bind for the first parameter (name)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char *)data_name;
    bind[0].buffer_length = sizeof(data_name);
    bind[0].is_null = 0;
    bind[0].length = &name_length;

    // Bind for the second parameter (address)
    bind[1].buffer_type = MYSQL_TYPE_STRING;
    bind[1].buffer = (char *)data_address;
    bind[1].buffer_length = sizeof(data_address);
    bind[1].is_null = 0;
    bind[1].length = &address_length;

    // Bind the parameters to the statement handle
    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Loop through all the rows in our 'values' array
    for (int i = 0; i < num_rows; i++) {
        // Set the data for the current row
        strncpy(data_name, values[i][0], sizeof(data_name));
        strncpy(data_address, values[i][1], sizeof(data_address));
        name_length = strlen(data_name);
        address_length = strlen(data_address);

        // Execute the prepared statement
        if (mysql_stmt_execute(stmt) != 0) {
            fprintf(stderr, "mysql_stmt_execute() failed for row %d: %s\n", i + 1, mysql_stmt_error(stmt));
            // Decide if you want to stop on error or continue
        } else {
            // Add the number of affected rows from this execution to the total
            total_affected_rows += mysql_stmt_affected_rows(stmt);
        }
    }

    printf("Number of records inserted: %lld\n", total_affected_rows);

    // --- 5. Clean up ---
    mysql_stmt_close(stmt);
    mysql_close(con);

    return EXIT_SUCCESS;
}