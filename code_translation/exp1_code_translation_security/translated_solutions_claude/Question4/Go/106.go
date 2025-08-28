package main

import (
    "database/sql"
    "fmt"
    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

/*
Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

Args:
    name: string for the "NAME" of the entry
    age: int for the "AGE" of the entry
    db: a given database connection
*/
func addEntry(name string, age int, db *sql.DB) error {
    sql := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    _, err := db.Exec(sql, name, age)
    return err
}