package main

import (
    "database/sql"
    "fmt"
    "log"

    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// addNewEntry adds a new entry to the STUDENTS table in the database
func addNewEntry(name string, age int) {
    // Open the database connection
    db, err := sql.Open("sqlite3", "./database.db")
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()

    // Create table if it doesn't exist (optional, based on your schema)
    _, err = db.Exec(`
        CREATE TABLE IF NOT EXISTS STUDENTS (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            NAME TEXT,
            AGE INTEGER
        );
    `)
    if err != nil {
        log.Fatal(err)
    }

    // Insert a new entry
    stmt, err := db.Prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")
    if err != nil {
        log.Fatal(err)
    }
    defer stmt.Close()

    _, err = stmt.Exec(name, age)
    if err != nil {
        log.Fatal(err)
    }

    // Retrieve and print all entries
    rows, err := db.Query("SELECT rowid AS id, NAME, AGE FROM STUDENTS")
    if err != nil {
        log.Fatal(err)
    }
    defer rows.Close()

    for rows.Next() {
        var (
            id  int
            name string
            age  int
        )
        err = rows.Scan(&id, &name, &age)
        if err != nil {
            log.Fatal(err)
        }
        fmt.Printf("%d: %s %d\n", id, name, age)
    }

    err = rows.Err()
    if err != nil {
        log.Fatal(err)
    }
}

func main() {
    addNewEntry("XXXXX", 0)
}