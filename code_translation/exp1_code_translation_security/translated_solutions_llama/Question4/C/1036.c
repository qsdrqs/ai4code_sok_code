#include <stdio.h>
#include <sqlite3.h>

// Callback function for printing rows
static int callback(void* data, int argc, char** argv, char** azColName) {
    for (int i = 0; i < argc; i++) {
        printf("%s: %s %s\n", argv[0], argv[1], argv[2]);
    }
    return 0;
}

// Function to add a new entry to the database
void add_new_entry(const char* name, int age) {
    sqlite3* db;
    char* errorMessage;

    // Open the database
    int rc = sqlite3_open("database.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return;
    }

    // Prepare and execute the INSERT statement
    char* sql = "INSERT INTO STUDENTS VALUES (?,?)";
    sqlite3_stmt* stmt;
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
    }

    sqlite3_finalize(stmt);

    // Execute the SELECT statement and print rows
    sql = "SELECT rowid AS id, NAME, AGE FROM STUDENTS";
    rc = sqlite3_exec(db, sql, callback, NULL, &errorMessage);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errorMessage);
        sqlite3_free(errorMessage);
    }

    // Close the database
    sqlite3_close(db);
}

int main() {
    add_new_entry("XXXXX", 0);
    return 0;
}