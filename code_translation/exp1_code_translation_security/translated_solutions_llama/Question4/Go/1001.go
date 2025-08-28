package main

import (
    "database/sql"
    "fmt"
    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// AddSQLentry adds a new entry to the students table
func AddSQLentry(db *sql.DB, name string, age int) {
    sqlStmt := "INSERT INTO students (name, age) VALUES (?, ?)"
    result, err := db.Exec(sqlStmt, name, age)
    if err != nil {
        fmt.Println(err)
        return
    }

    id, err := result.LastInsertId()
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Printf("User added with id: %d\n", id)
}

func main() {
    // Open a connection to the database
    db, err := sql.Open("sqlite3", "./example.db")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer db.Close()

    // Create the students table if it doesn't exist
    _, err = db.Exec(`
        CREATE TABLE IF NOT EXISTS students (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            age INTEGER NOT NULL
        );
    `)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Add a new student
    AddSQLentry(db, "John Doe", 25)
}