package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

func inserter(name string, age int) {
    // Create connection string
    dsn := "yourusername:yourpassword@tcp(localhost:3306)/mydb"
    
    // Open database connection
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()
    
    // Test the connection
    err = db.Ping()
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println("Connected!")
    
    // Prepare SQL statement with placeholders
    sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    
    // Execute the query with parameters
    _, err = db.Exec(sqlStatement, name, age)
    if err != nil {
        log.Fatal(err)
    }
    
    fmt.Println("1 record inserted")
}

func main() {
    // Example usage
    inserter("John Doe", 25)
}