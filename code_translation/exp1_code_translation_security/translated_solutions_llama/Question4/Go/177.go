package main

import (
    "database/sql"
    "fmt"

    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// addStudent adds a new student to the database
func addStudent(name string, age int) error {
    // Open a connection to the database
    db, err := sql.Open("sqlite3", "./students.db")
    if err != nil {
        return err
    }
    defer db.Close()

    // Create the STUDENTS table if it doesn't exist
    _, err = db.Exec(`
        CREATE TABLE IF NOT EXISTS STUDENTS (
            name TEXT,
            age INTEGER
        );
    `)
    if err != nil {
        return err
    }

    // Insert a new student into the STUDENTS table
    _, err = db.Exec("INSERT INTO STUDENTS VALUES (?, ?);", name, age)
    if err != nil {
        return err
    }

    return nil
}

func main() {
    // Example usage
    name := "John Doe"
    age := 20
    err := addStudent(name, age)
    if err != nil {
        fmt.Println(err)
    } else {
        fmt.Printf("Added student: %s, %d\n", name, age)
    }
}