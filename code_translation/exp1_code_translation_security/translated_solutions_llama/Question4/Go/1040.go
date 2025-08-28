package main

import (
    "database/sql"
    "fmt"
    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// addNewEntry adds a new entry to the STUDENTS table
func addNewEntry(db *sql.DB, name string, age int) error {
    // Create table if it doesn't exist
    _, err := db.Exec(`
        CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME text,
            AGE INT
        );
    `)
    if err != nil {
        return err
    }

    // Insert new entry
    stmt, err := db.Prepare("INSERT INTO STUDENTS VALUES (?, ?)")
    if err != nil {
        return err
    }
    defer stmt.Close()

    _, err = stmt.Exec(name, age)
    return err
}

func main() {
    // Open an in-memory SQLite database
    db, err := sql.Open("sqlite3", ":memory:")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer db.Close()

    // Add new entries
    if err := addNewEntry(db, "XXXXX", 0); err != nil {
        fmt.Println(err)
        return
    }
    if err := addNewEntry(db, "XXXXX", 0); err != nil {
        fmt.Println(err)
        return
    }

    // Retrieve and print all entries
    rows, err := db.Query("SELECT NAME, AGE FROM STUDENTS")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer rows.Close()

    for rows.Next() {
        var (
            name string
            age  int
        )
        err = rows.Scan(&name, &age)
        if err != nil {
            fmt.Println(err)
            return
        }
        fmt.Printf("Name: %s, Age: %d\n", name, age)
    }
}