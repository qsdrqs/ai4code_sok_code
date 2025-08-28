package main

import (
    "database/sql"
    "log"

    _ "github.com/mattn/go-sqlite3"
)

// AddStudent inserts a student into the STUDENTS table.
// It logs "inserted <error>" regardless of success or failure.
func AddStudent(db *sql.DB, name string, age int) {
    _, err := db.Exec("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", name, age)
    log.Printf("inserted %v", err)
}