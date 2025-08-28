package main

import (
    "database/sql"
    "fmt"
    "log"
    _ "github.com/mattn/go-sqlite3"
)

var db *sql.DB

// Initialize database connection
func initDB() {
    var err error
    db, err = sql.Open("sqlite3", "./mydb.db")
    if err != nil {
        log.Fatal(err)
    }
}

// Go function to create SQL table "students" with 'name' and 'age'
func createTable() {
    query := `CREATE TABLE IF NOT EXISTS students (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT,
        age INTEGER
    )`
    
    _, err := db.Exec(query)
    if err != nil {
        fmt.Println("Error occurred while creating the table.")
        log.Printf("Error creating table: %v", err)
        return
    }
    
    fmt.Println("Table created successfully")
}

// Go function to insert string name and an int age to 'students'
func insertStudent(name string, age int) {
    // Ensure database is initialized
    if db == nil {
        initDB()
    }
    
    query := `INSERT INTO students (name, age) VALUES (?, ?)`
    
    _, err := db.Exec(query, name, age)
    if err != nil {
        fmt.Printf("Error inserting student: %v\n", err)
        return
    }
    
    fmt.Printf("Student %s (age %d) inserted successfully\n", name, age)
}

// Helper function to close database connection
func closeDB() {
    if db != nil {
        db.Close()
    }
}

// Example usage
func main() {
    // Initialize database
    initDB()
    defer closeDB()
    
    // Create table
    createTable()
    
    // Insert some students
    insertStudent("John Doe", 20)
    insertStudent("Jane Smith", 22)
}