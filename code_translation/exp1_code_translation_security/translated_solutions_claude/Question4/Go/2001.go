package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

// Assuming you have a global database connection
var db *sql.DB

func add(name string, age int) {
    // Add name, age to students table in MySQL
    query := "INSERT INTO students (name, age) VALUES (?, ?)"
    
    _, err := db.Exec(query, name, age)
    if err != nil {
        log.Fatal(err)
    }
    
    fmt.Printf("Added %s to students table\n", name)
}

// Example of how to initialize the database connection
func initDB() {
    var err error
    // Replace with your actual database connection string
    db, err = sql.Open("mysql", "username:password@tcp(localhost:3306)/database_name")
    if err != nil {
        log.Fatal(err)
    }
    
    // Test the connection
    err = db.Ping()
    if err != nil {
        log.Fatal(err)
    }
}

func main() {
    initDB()
    defer db.Close()
    
    // Example usage
    add("John Doe", 25)
}