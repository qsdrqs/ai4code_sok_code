#include <stdio.h>
#include <sqlite3.h>

// Callback function for sqlite3_exec to process each row
static int callback(void *data, int argc, char **argv, char **azColName) {
    // Print row data: id, name, age
    if (argc == 3) {
        printf("%s: %s %s\n", argv[0], argv[1], argv[2]);
    }
    return 0;
}

// Function to add a new entry and print all records
void add_new_entry(const char *name, int age) {
    sqlite3 *db;
    int rc = sqlite3_open("database.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        return;
    }

    // Prepare and execute the INSERT statement
    const char *insert_sql = "INSERT INTO STUDENTS VALUES (?, ?);";
    sqlite3_stmt *insert_stmt;
    rc = sqlite3_prepare_v2(db, insert_sql, -1, &insert_stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare insert statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    sqlite3_bind_text(insert_stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(insert_stmt, 2, age);

    rc = sqlite3_step(insert_stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Insert failed: %s\n", sqlite3_errmsg(db));
    }

    sqlite3_finalize(insert_stmt);

    // Execute SELECT query and print results
    char *err_message = NULL;
    const char *select_sql = "SELECT rowid AS id, NAME, AGE FROM STUDENTS;";
    rc = sqlite3_exec(db, select_sql, callback, NULL, &err_message);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_message);
        sqlite3_free(err_message);
    }

    sqlite3_close(db);
}

// Entry point
int main() {
    add_new_entry("XXXXX", 0);
    return 0;
}