package main

import (
    "database/sql"
    "fmt"
    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// CreateTable creates a SQL table "students" with 'name' and "age"
func CreateTable(db *sql.DB) error {
    query := `
        CREATE TABLE IF NOT EXISTS students (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            age INTEGER
        );
    `
    _, err := db.Exec(query)
    if err != nil {
        return fmt.Errorf("error creating table: %w", err)
    }
    fmt.Println("Table created successfully")
    return nil
}

// InsertStudent inserts a string name and an int age to 'students'
func InsertStudent(db *sql.DB, name string, age int) error {
    query := "INSERT INTO students (name, age) VALUES (?, ?)"
    _, err := db.Exec(query, name, age)
    if err != nil {
        return fmt.Errorf("error inserting student: %w", err)
    }
    return nil
}

func main() {
    // Open a connection to the SQLite database
    db, err := sql.Open("sqlite3", "./mydb.db")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer db.Close()

    // Create the table
    err = CreateTable(db)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Insert a student
    name := "John Doe"
    age := 25
    err = InsertStudent(db, name, age)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Printf("Inserted student: %s, %d\n", name, age)
}