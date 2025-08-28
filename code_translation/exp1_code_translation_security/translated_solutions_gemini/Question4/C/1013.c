#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // The MySQL C API header

// --- Helper Functions (Dependencies) ---
// These replace the non-standard readLine and readInt functions.

// A simple function to read a line of text from the user.
// NOTE: Uses a static buffer, so it's not thread-safe.
char* readLine(const char* prompt) {
    static char buffer[256];
    printf("%s", prompt);
    fflush(stdout); // Ensure the prompt is displayed before reading input

    if (fgets(buffer, sizeof(buffer), stdin) != NULL) {
        // Remove the trailing newline character, if it exists
        buffer[strcspn(buffer, "\n")] = 0;
        return buffer;
    }
    return ""; // Return empty string on error
}

// A simple function to read an integer from the user.
int readInt(const char* prompt) {
    char* input_str = readLine(prompt);
    return atoi(input_str); // Convert string to integer
}

// --- Main Logic ---

// A helper function to handle and display MySQL errors, then exit.
void finish_with_error(MYSQL *con) {
  fprintf(stderr, "%s\n", mysql_error(con));
  if (con != NULL) {
      mysql_close(con);
  }
  exit(1);
}

void insert() {
    // --- Connection Details ---
    char *host = "localhost";
    char *user = "yourusername";
    char *password = "yourpassword";
    char *database = "mydb";

    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }

    // --- con.connect() ---
    if (mysql_real_connect(con, host, user, password, database, 0, NULL, 0) == NULL) {
        finish_with_error(con);
    }

    printf("Connected!\n");

    // --- Get user input ---
    char name[256];
    // We must copy the result of readLine because it uses a static buffer
    strcpy(name, readLine("enter a name: "));
    int age = readInt("enter an age: ");

    // --- Prepare the SQL statement ---
    // Using '?' as placeholders is the standard for prepared statements
    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    MYSQL_STMT *stmt = mysql_stmt_init(con);

    if (!stmt) {
        finish_with_error(con);
    }

    if (mysql_stmt_prepare(stmt, sql, strlen(sql))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    // --- Bind parameters to the statement ---
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind)); // Zero out the structure

    // Parameter 1: Name (String)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char *)name;
    bind[0].buffer_length = strlen(name);

    // Parameter 2: Age (Integer)
    bind[1].buffer_type = MYSQL_TYPE_LONG; // LONG is used for int
    bind[1].buffer = (char *)&age;
    bind[1].is_unsigned = 0;
    bind[1].length = 0;

    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    // --- con.query() ---
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        finish_with_error(con);
    }

    // --- Get the result ---
    my_ulonglong affected_rows = mysql_stmt_affected_rows(stmt);
    printf("Number of records inserted: %llu\n", affected_rows);

    // --- Clean up ---
    mysql_stmt_close(stmt);
    mysql_close(con);
}

int main() {
    insert();
    return 0;
}