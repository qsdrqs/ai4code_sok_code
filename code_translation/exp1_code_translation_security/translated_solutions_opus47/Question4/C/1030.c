#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

void createDatabase();
void createTables(sqlite3 *db);
void createTables2(sqlite3 *db);
void insert(sqlite3 *db, const char *name, int age);

// async issues (note: C is synchronous, so no async issues here)
int main() {
    //sqlite3 *db;
    //sqlite3_open("./temp.db", &db);
    createDatabase();
    /*
    createTables(db);
    // setTimeout 500ms:
    insert(db, "John Doe", 30);
    // setTimeout 1000ms:
    // select * FROM students; print each row's name and age
    */
    return 0;
}

void createDatabase() {
    printf("created database\n");
    sqlite3 *newdb;
    int rc = sqlite3_open("temp.db", &newdb);
    if (rc != SQLITE_OK) {
        printf("Getting error %s\n", sqlite3_errmsg(newdb));
        exit(1);
    }
    createTables2(newdb);
    sqlite3_close(newdb);
}

void createTables(sqlite3 *db) {
    printf("pre-created tables\n");
    sqlite3_exec(db, "CREATE TABLE students(name TEXT,age INT)", 0, 0, 0);
}

void createTables2(sqlite3 *db) {
    printf("pre-created tables\n");
    char *errMsg = 0;
    sqlite3_exec(db, "CREATE TABLE students(name TEXT,age INT)", 0, 0, &errMsg);
    if (errMsg) sqlite3_free(errMsg);
    insert(db, "John Doe", 30);
}

void insert(sqlite3 *db, const char *name, int age) {
    const char *sql = "insert into students(name, age) VALUES (?,?)";
    sqlite3_stmt *stmt;
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        printf("%s\n", sqlite3_errmsg(db));
        return;
    }
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        printf("%s\n", sqlite3_errmsg(db));
    } else {
        printf("row added successfully\n");
    }
    sqlite3_finalize(stmt);
}