#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>
#include <string.h>

// Global database connection
sqlite3 *db;

/* C function to create SQL table "students" with 'name' and 'age' */
void createTable() {
    char *sql = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER);";
    char *errMsg = 0;
    int rc;
    
    rc = sqlite3_exec(db, sql, 0, 0, &errMsg);
    
    if (rc != SQLITE_OK) {
        printf("Error occurred while creating the table: %s\n", errMsg);
        sqlite3_free(errMsg);
    } else {
        printf("Table created successfully\n");
    }
}

/* C function to insert string name and an int age to 'students' */
void insertStudent(const char* name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?);";
    int rc;
    
    // Prepare the SQL statement
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    
    if (rc != SQLITE_OK) {
        printf("Error preparing statement: %s\n", sqlite3_errmsg(db));
        return;
    }
    
    // Bind parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);
    
    // Execute the statement
    rc = sqlite3_step(stmt);
    
    if (rc != SQLITE_DONE) {
        printf("Error inserting student: %s\n", sqlite3_errmsg(db));
    } else {
        printf("Student inserted successfully\n");
    }
    
    // Finalize the statement
    sqlite3_finalize(stmt);
}

/* Helper function to open database connection */
int openDatabase(const char* dbName) {
    int rc = sqlite3_open(dbName, &db);
    
    if (rc) {
        printf("Can't open database: %s\n", sqlite3_errmsg(db));
        return rc;
    }
    
    return SQLITE_OK;
}

/* Helper function to close database connection */
void closeDatabase() {
    if (db) {
        sqlite3_close(db);
        db = NULL;
    }
}

/* Example usage */
int main() {
    // Open database
    if (openDatabase("mydb.db") != SQLITE_OK) {
        return 1;
    }
    
    // Create table
    createTable();
    
    // Insert some students
    insertStudent("John Doe", 20);
    insertStudent("Jane Smith", 22);
    
    // Close database
    closeDatabase();
    
    return 0;
}