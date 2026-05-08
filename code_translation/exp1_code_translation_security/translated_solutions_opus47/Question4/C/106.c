#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sqlite3.h>

/*
Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

Args:
    name: string for the "NAME" of the entry
    age: int for the "AGE" of the entry
    db: a given database connection
*/
void addEntry(const char *name, int age, sqlite3 *db) {
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    sqlite3_exec(db, sql, NULL, NULL, NULL);
}