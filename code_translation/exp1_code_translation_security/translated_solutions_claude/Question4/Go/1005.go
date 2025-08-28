package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

var db *sql.DB

func init() {
    var err error
    // Replace with your actual connection string
    db, err = sql.Open("mysql", "username:password@tcp(localhost:3306)/database_name")
    if err != nil {
        log.Fatal(err)
    }
}

func addStudent(name string, age int) {
    err := db.Ping()
    if err != nil {
        panic(err)
    }
    
    _, err = db.Exec("INSERT INTO STUDENTS VALUES (?, ?)", name, age)
    if err != nil {
        // Handle error as needed
        fmt.Printf("Error inserting student: %v\n", err)
    }
}

func main() {
    defer db.Close()
    
    // Example usage
    addStudent("John Doe", 20)
}