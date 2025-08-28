#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/**
 * Adds a new student to the STUDENTS table.
 *
 * @param db    SQLite database handle.
 * @param name  Name of the student (string).
 * @param age   Age of the student (integer).
 * @return      Number of rows affected by the insert operation.
 */
int addStudent(sqlite3 *db, const char *name, int age) {
    // Construct the SQL statement using string interpolation
    char *sql = sqlite3_mprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    char *errmsg = NULL;

    // Execute the SQL statement
    int rc = sqlite3_exec(db, sql, NULL, NULL, &errmsg);

    // Free the dynamically allocated SQL string
    sqlite3_free(sql);

    // Handle any errors
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errmsg);
        sqlite3_free(errmsg);
    }

    // Return the number of rows affected
    return sqlite3_changes(db);
}

int main() {
    sqlite3 *db;
    int rc;

    // Open the SQLite database file
    rc = sqlite3_open("database.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }

    // Create the STUDENTS table if it doesn't exist
    char *errmsg = NULL;
    const char *create_sql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";
    rc = sqlite3_exec(db, create_sql, NULL, NULL, &errmsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to create table: %s\n", errmsg);
        sqlite3_free(errmsg);
    }

    // Add a new student
    int affected = addStudent(db, "XXXXX", 0);
    printf("Rows affected: %d\n", affected);

    // Query and print all students
    sqlite3_stmt *stmt;
    const char *select_sql = "SELECT NAME, AGE FROM STUDENTS";
    rc = sqlite3_prepare_v2(db, select_sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }

    printf("Students in the database:\n");
    while (sqlite3_step(stmt) == SQLITE_ROW) {
        const unsigned char *name = sqlite3_column_text(stmt, 0);
        int age = sqlite3_column_int(stmt, 1);
        printf("%s %d\n", name, age);
    }

    // Clean up
    sqlite3_finalize(stmt);
    sqlite3_close(db);

    return 0;
}