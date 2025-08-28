package main

import (
    "database/sql"
    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// AddEntry adds a new entry to the STUDENTS table in the database.
// 
// Args:
//     name: string for the NAME of the entry
//     age: int for the AGE of the entry
//     db: a given database connection
func AddEntry(name string, age int, db *sql.DB) error {
    sqlStmt := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    _, err := db.Exec(sqlStmt, name, age)
    return err
}

func main() {
    // Example usage
    db, err := sql.Open("sqlite3", "./example.db") // Open a SQLite database file
    if err != nil {
        panic(err)
    }
    defer db.Close()

    // Create table if it doesn't exist
    _, err = db.Exec(`
        CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT,
            AGE INTEGER
        );
    `)
    if err != nil {
        panic(err)
    }

    // Add a new entry
    err = AddEntry("John Doe", 20, db)
    if err != nil {
        panic(err)
    }
}