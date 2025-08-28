#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h> // The C equivalent of require('mysql')

// This helper function prints the error, closes the connection, and exits.
void finish_with_error(MYSQL *conn) {
  fprintf(stderr, "%s\n", mysql_error(conn));
  mysql_close(conn);
  exit(1);
}

/**
 * @brief Inserts a static record into the STUDENTS table.
 * 
 * @param name The name of the student (unused in this implementation, matching the original JS).
 * @param age The age of the student (unused in this implementation, matching the original JS).
 */
void insertStudent(const char* name, int age) {
  // --- Configuration: Replaces the 'config' object from JavaScript ---
  const char *server   = "localhost";
  const char *user     = "your_username";
  const char *password = "your_password";
  const char *database = "your_database";
  // --------------------------------------------------------------------

  MYSQL *conn = mysql_init(NULL);

  // Check if initialization was successful
  if (conn == NULL) {
      fprintf(stderr, "mysql_init() failed\n");
      exit(1);
  }

  // Create a connection to the database
  // This is the equivalent of mysql.createConnection(config)
  if (mysql_real_connect(conn, server, user, password, database, 0, NULL, 0) == NULL) {
      finish_with_error(conn);
  }

  // Insert statement (hardcoded, same as the original JS code)
  // Note: MySQL treats 'true' as 1.
  const char *sql = "INSERT INTO STUDENTS(title, completed) "
                    "VALUES('Learn how to insert a new row', 1)";

  // Execute the insert statement
  // This is the equivalent of connection.query(sql)
  if (mysql_query(conn, sql)) {
      finish_with_error(conn);
  }

  printf("Successfully inserted a new row.\n");
  
  // Close the connection, equivalent to connection.end()
  mysql_close(conn);
}

int main() {
  // Call the function, just like in the JavaScript example
  insertStudent("XXXXX", 0);
  
  return 0;
}