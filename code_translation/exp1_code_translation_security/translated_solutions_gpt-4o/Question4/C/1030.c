#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Function declarations
void createDatabase();
void createTables(sqlite3 *db);
void createTables2(sqlite3 *db);
void insert(sqlite3 *db, const char *name, int age);

int main() {
    createDatabase();
    return 0;
}

void createDatabase() {
    printf("created database\n");
    sqlite3 *db;
    int rc = sqlite3_open("temp.db", &db);

    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        exit(1);
    } else {
        createTables2(db);
    }
}

void createTables(sqlite3 *db) {
    printf("pre-created tables\n");
    char *errMsg = 0;
    int rc = sqlite3_exec(db, "CREATE TABLE students(name TEXT, age INT);", 0, 0, &errMsg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    }
}

void createTables2(sqlite3 *db) {
    printf("pre-created tables\n");
    char *errMsg = 0;
    int rc = sqlite3_exec(db, "CREATE TABLE students(name TEXT, age INT);", 0, 0, &errMsg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    } else {
        insert(db, "John Doe", 30);
    }
}

void insert(sqlite3 *db, const char *name, int age) {
    char *errMsg = 0;
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO students(name, age) VALUES('%s', %d);", name, age);

    int rc = sqlite3_exec(db, sql, 0, 0, &errMsg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    } else {
        printf("row added successfully\n");
    }
}