package main

import (
    "database/sql"
    "log"
    _ "github.com/go-sql-driver/mysql"
)

// Assuming con is a global database connection
var con *sql.DB

func createEntry(name string, age int) {
    // In Go, we typically establish the connection once and reuse it
    // rather than connecting for each operation
    
    // Prepare the SQL statement
    stmt, err := con.Prepare("INSERT INTO STUDENTS (name, age) VALUES (?, ?)")
    if err != nil {
        log.Fatal(err)
    }
    defer stmt.Close()
    
    // Execute the prepared statement
    _, err = stmt.Exec(name, age)
    if err != nil {
        log.Fatal(err)
    }
}

// Alternative implementation without prepared statement
func createEntryAlternative(name string, age int) {
    _, err := con.Exec("INSERT INTO STUDENTS (name, age) VALUES (?, ?)", name, age)
    if err != nil {
        log.Fatal(err)
    }
}

// Example of how to initialize the database connection
func initDB() {
    var err error
    // Replace with your actual database connection string
    con, err = sql.Open("mysql", "username:password@tcp(localhost:3306)/database_name")
    if err != nil {
        log.Fatal(err)
    }
    
    // Test the connection
    err = con.Ping()
    if err != nil {
        log.Fatal(err)
    }
}