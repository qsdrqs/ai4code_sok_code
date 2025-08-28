package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

func insertion(name string, age int) {
    // Create database connection
    // You'll need to replace "username:password@tcp(localhost:3306)/database_name" with your actual connection string
    db, err := sql.Open("mysql", "username:password@tcp(localhost:3306)/database_name")
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()
    
    // Test the connection
    err = db.Ping()
    if err != nil {
        log.Fatal(err)
    }
    
    // Prepare and execute the SQL statement
    sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    _, err = db.Exec(sqlStatement, name, age)
    if err != nil {
        log.Fatal(err)
    }
    
    fmt.Println("Record inserted successfully")
}

func main() {
    // Example usage
    insertion("John Doe", 20)
}