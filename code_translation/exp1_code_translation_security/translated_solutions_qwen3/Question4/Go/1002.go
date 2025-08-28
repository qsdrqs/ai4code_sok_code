package main

import (
    "database/sql"
    "log"

    _ "github.com/denisenkom/go-mssqldb"
)

// insertUser inserts a new student into the STUDENTS table.
// Assumes that the provided *sql.DB connection is valid and active.
func insertUser(conn *sql.DB, name string, age int) {
    const query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (@NAME, @AGE);"

    // Execute the query with named parameters
    _, err := conn.Exec(query, sql.Named("NAME", name), sql.Named("AGE", age))
    if err != nil {
        log.Println("Error inserting user:", err)
    }
}