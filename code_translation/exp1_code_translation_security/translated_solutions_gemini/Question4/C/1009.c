#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // MySQL C API header

// A helper function to print errors and exit, similar to "throw err"
void finish_with_error(MYSQL *con) {
  fprintf(stderr, "%s\n", mysql_error(con));
  if (con != NULL) {
    mysql_close(con);
  }
  exit(1);
}

/**
 * @brief Inserts a new student record into the database.
 *
 * This function is the C equivalent of the JavaScript createEntry function.
 * It uses a prepared statement to safely insert the name and age.
 *
 * @param con An active and initialized MySQL connection handle.
 * @param name The name of the student to insert.
 * @param age The age of the student to insert.
 */
void create_entry(MYSQL *con, const char *name, int age) {
  // 1. Prepare the SQL query with placeholders (?)
  const char *query = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
  MYSQL_STMT *stmt;
  MYSQL_BIND params[2];
  
  // Initialize a statement handle
  stmt = mysql_stmt_init(con);
  if (!stmt) {
    fprintf(stderr, "mysql_stmt_init(), out of memory\n");
    exit(1);
  }

  // Prepare the SQL statement
  if (mysql_stmt_prepare(stmt, query, strlen(query))) {
    fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
    mysql_stmt_close(stmt);
    finish_with_error(con);
  }

  // 2. Bind the parameters to the placeholders
  memset(params, 0, sizeof(params)); // Zero out the structure

  // Parameter 1: name (string)
  unsigned long name_length = strlen(name);
  params[0].buffer_type = MYSQL_TYPE_STRING;
  params[0].buffer = (char *)name;
  params[0].buffer_length = name_length;
  params[0].is_null = 0;
  params[0].length = &name_length;

  // Parameter 2: age (integer)
  params[1].buffer_type = MYSQL_TYPE_LONG; // MYSQL_TYPE_LONG is used for int
  params[1].buffer = (char *)&age;
  params[1].is_null = 0;
  params[1].length = 0; // Not needed for fixed-length types

  // Bind the parameters to the statement
  if (mysql_stmt_bind_param(stmt, params)) {
    fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
    mysql_stmt_close(stmt);
    finish_with_error(con);
  }

  // 3. Execute the prepared statement
  if (mysql_stmt_execute(stmt)) {
    fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
    mysql_stmt_close(stmt);
    finish_with_error(con);
  }

  printf("Successfully inserted %s with age %d.\n", name, age);

  // 4. Clean up the statement handle
  mysql_stmt_close(stmt);
}

// Main function to demonstrate usage
int main() {
  // --- Connection Details (replace with your own) ---
  char *host = "127.0.0.1";
  char *user = "user";
  char *pass = "password";
  char *dbname = "testdb";
  unsigned int port = 3306;

  MYSQL *con = mysql_init(NULL);

  if (con == NULL) {
      fprintf(stderr, "mysql_init() failed\n");
      exit(1);
  }

  // The equivalent of con.connect() from the JavaScript code
  if (mysql_real_connect(con, host, user, pass, dbname, port, NULL, 0) == NULL) {
      finish_with_error(con);
  }
  
  printf("MySQL connection successful.\n");

  // Call the function to create a new entry
  create_entry(con, "Alice", 30);
  create_entry(con, "Bob", 25);

  // Close the connection when done
  mysql_close(con);

  return 0;
}