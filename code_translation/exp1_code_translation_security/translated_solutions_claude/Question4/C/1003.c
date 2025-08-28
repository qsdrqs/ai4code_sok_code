#include <stdio.h>
#include <sqlite3.h>

// Callback function for the database operation
static void insert_callback(void* data, int result) {
    if (result == SQLITE_OK) {
        printf("inserted (null)\n");
    } else {
        printf("inserted %s\n", sqlite3_errstr(result));
    }
}

// db: sqlite3* database connection, assumed to contain a STUDENTS (NAME, AGE) table
// name: student name string
// age: student age integer
//
// This function doesn't do any error checking on the types or sizes of `name`
// and `age`, and neither does Sqlite, so if you care, don't pass things that
// you don't want in your database.
void addStudent(sqlite3* db, const char* name, int age) {
    sqlite3_stmt* stmt;
    int rc;
    
    // Prepare the SQL statement
    rc = sqlite3_prepare_v2(db, "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", -1, &stmt, NULL);
    
    if (rc == SQLITE_OK) {
        // Bind the parameters
        sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
        sqlite3_bind_int(stmt, 2, age);
        
        // Execute the statement
        rc = sqlite3_step(stmt);
        
        // Call the callback to mimic the JavaScript behavior
        insert_callback(NULL, (rc == SQLITE_DONE) ? SQLITE_OK : rc);
    } else {
        // Call the callback with the error
        insert_callback(NULL, rc);
    }
    
    // Clean up
    sqlite3_finalize(stmt);
}