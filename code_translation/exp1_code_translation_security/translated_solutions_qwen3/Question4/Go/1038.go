package main

import (
    "database/sql"
    "log"

    _ "github.com/go-sql-driver/mysql"
)

// Insertion inserts a new student into the STUDENTS table.
func Insertion(name string, age int) {
    // Define the Data Source Name (DSN) for the MySQL connection.
    // Replace the placeholders with your actual database credentials.
    dsn := "root:password@tcp(127.0.0.1:3306)/mydatabase"

    // Open a connection to the database.
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        log.Fatalf("Failed to open database connection: %v", err)
    }
    defer db.Close() // Ensure the connection is closed after the function exits.

    // Ping the database to verify the connection is established.
    if err := db.Ping(); err != nil {
        log.Fatalf("Failed to connect to the database: %v", err)
    }

    // Execute the INSERT statement using parameterized query to prevent SQL injection.
    result, err := db.Exec("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", name, age)
    if err != nil {
        log.Fatalf("Failed to insert record: %v", err)
    }

    // Optionally, you can log or use the result (e.g., last insert ID).
    lastInsertID, _ := result.LastInsertId()
    log.Printf("Inserted student with ID: %d", lastInsertID)
}