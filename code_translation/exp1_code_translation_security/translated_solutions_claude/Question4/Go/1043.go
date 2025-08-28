package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql" // MySQL driver (adjust based on your database)
)

// Assuming there's a SQL table called STUDENTS available

var db *sql.DB // Global database connection

// adds a new entry to SQL table "STUDENTS"
func add(name string, age int) {
    sqlQuery := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
    fmt.Println(sqlQuery)
    
    _, err := db.Exec(sqlQuery)
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println("1 record inserted")
}

// Example of how to initialize the database connection
func init() {
    var err error
    // Adjust connection string based on your database type and credentials
    db, err = sql.Open("mysql", "username:password@tcp(localhost:3306)/database_name")
    if err != nil {
        log.Fatal(err)
    }
    
    if err = db.Ping(); err != nil {
        log.Fatal(err)
    }
}