#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sqlite3.h>

// Global database connection
sqlite3 *db;

// Function to add a student to the database
int addStudent(const char* name, int age) {
    char sql[256];
    char *err_msg = 0;
    int rc;
    
    // Construct the SQL query
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    
    // Execute the SQL statement
    rc = sqlite3_exec(db, sql, 0, 0, &err_msg);
    
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        return 0;
    }
    
    // Get the number of rows affected
    int rows_affected = sqlite3_changes(db);
    
    // Print result
    printf("Rows affected: %d\n", rows_affected);
    
    return rows_affected;
}

// Callback function for displaying query results
static int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    for (int i = 0; i < argc; i++) {
        printf("%s ", argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0;
}

int main() {
    int rc;
    char *err_msg = 0;
    
    // Create/open database
    rc = sqlite3_open("./database.db", &db);
    
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }
    
    // Create table (uncomment if needed - only do this once!)
    /*
    char *create_sql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)";
    rc = sqlite3_exec(db, create_sql, 0, 0, &err_msg);
    
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
    }
    */
    
    // Test example
    addStudent("XXXXX", 0);
    
    // Display contents of database
    printf("Database contents:\n");
    rc = sqlite3_exec(db, "SELECT * FROM STUDENTS", callback, 0, &err_msg);
    
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
    }
    
    // Close database
    sqlite3_close(db);
    
    return 0;
}