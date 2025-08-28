package main

import (
    "database/sql"
    "log"
    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// createEntry inserts a new student into the STUDENTS table.
func createEntry(name string, age int) {
    // Open a new connection to the database each time
    db, err := sql.Open("mysql", "user:password@tcp(127.0.0.1:3306)/mydatabase")
    if err != nil {
        log.Fatal("Failed to open database connection:", err)
    }
    defer db.Close()

    // Simulate the 'connect' step by pinging the database
    if err := db.Ping(); err != nil {
        log.Fatal("Failed to connect to the database:", err)
    }

    // Execute the insert query
    _, err = db.Exec("INSERT INTO STUDENTS (name, age) VALUES (?, ?)", name, age)
    if err != nil {
        log.Fatal("Failed to insert record:", err)
    }
}