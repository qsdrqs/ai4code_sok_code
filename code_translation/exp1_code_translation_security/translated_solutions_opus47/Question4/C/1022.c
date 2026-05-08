#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

sqlite3 *db;

void student_write(const char *name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
    
    if (sqlite3_prepare_v2(db, sql, -1, &stmt, NULL) == SQLITE_OK) {
        sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
        sqlite3_bind_int(stmt, 2, age);
        sqlite3_step(stmt);
    }
    
    sqlite3_finalize(stmt);
}

static int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    // Mimics console.log(row, row) - prints each row's data twice
    for (int j = 0; j < 2; j++) {
        printf("{ ");
        for (int i = 0; i < argc; i++) {
            printf("%s: %s", azColName[i], argv[i] ? argv[i] : "NULL");
            if (i < argc - 1) printf(", ");
        }
        printf(" }");
        if (j == 0) printf(" ");
    }
    printf("\n");
    return 0;
}

int main() {
    char *err_msg = NULL;
    int rc = sqlite3_open("/home/codex/Documents/User_Study_UI/database.db", &db);
    
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }
    
    student_write("XXXXX", 0);
    
    const char *sql = "SELECT * FROM STUDENTS";
    rc = sqlite3_exec(db, sql, callback, NULL, &err_msg);
    
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
    }
    
    sqlite3_close(db);
    return 0;
}