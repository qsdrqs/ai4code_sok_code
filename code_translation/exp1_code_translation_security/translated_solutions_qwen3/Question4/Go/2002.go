package main

import (
    "database/sql"
    _ "github.com/go-sql-driver/mysql"
)

// Assume 'connection' is a globally accessible *sql.DB instance
var connection *sql.DB

// addStudent inserts a new student into the STUDENTS table asynchronously.
func addStudent(name string, age int) {
    go func() {
        // Execute the SQL query asynchronously and ignore any errors
        _, _ = connection.Exec(`INSERT INTO STUDENTS(NAME, AGE)
VALUES(?,?)`, name, age)
    }()
}