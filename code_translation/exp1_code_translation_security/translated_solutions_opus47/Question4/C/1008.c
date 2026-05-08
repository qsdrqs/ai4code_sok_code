#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/* C function to create SQL table "students" with 'name' and 'age' */
void createTable(void) {
    sqlite3 *db;
    char *err_msg = NULL;
    int rc;

    rc = sqlite3_open("mydb.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    const char *sql = "CREATE TABLE IF NOT EXISTS students ("
                      "id INTEGER PRIMARY KEY, "
                      "name TEXT, "
                      "age INTEGER)";

    rc = sqlite3_exec(db, sql, NULL, NULL, &err_msg);

    if (rc != SQLITE_OK) {
        printf("Error occurred while creating the table.\n");
        sqlite3_free(err_msg);
    } else {
        printf("Table created successfully\n");
    }

    sqlite3_close(db);
}

/* C function to insert string name and an int age to 'students' */
void insertStudent(const char *name, int age) {
    sqlite3 *db;
    sqlite3_stmt *stmt;
    int rc;

    rc = sqlite3_open("mydb.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?)";

    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    /* Bind parameters (analogous to [name, age] in the JS version) */
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to insert record: %s\n", sqlite3_errmsg(db));
    }

    sqlite3_finalize(stmt);
    sqlite3_close(db);
}

/* Example usage */
int main(void) {
    createTable();
    insertStudent("John Doe", 20);
    insertStudent("Jane Smith", 22);
    return 0;
}