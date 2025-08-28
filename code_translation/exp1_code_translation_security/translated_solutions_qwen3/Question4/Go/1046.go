package main

import (
    "database/sql"
    "fmt"
    "log"

    _ "github.com/go-sql-driver/mysql"
)

// Global database connection
var db *sql.DB

func init() {
    // Hardcoded DSN (Data Source Name) to simulate the config.js file
    dsn := "user:password@tcp(127.0.0.1:3306)/testdb"

    // Open a connection to the database
    var err error
    db, err = sql.Open("mysql", dsn)
    if err != nil {
        log.Fatal("Failed to connect to the database:", err)
    }
}

// insertInto simulates the JavaScript function by inserting a student into the database
func insertInto(name string, age int) {
    // Construct the SQL query using string interpolation (not recommended for production)
    sqlStr := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %d)", name, age)

    // Execute the query
    _, err := db.Exec(sqlStr)
    if err != nil {
        log.Fatal("Failed to execute query:", err)
    }

    // Close the database connection after the operation
    db.Close()
}

func main() {
    // Example usage of the insertInto function
    insertInto("Alice", 25)
}