package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

func main() {
    // Create connection string
    dsn := "yourusername:yourpassword@tcp(127.0.0.1:3306)/mydb"
    
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
    
    // Prepare SQL statement
    sqlStatement := "INSERT INTO customers (name, address) VALUES (?, ?)"
    
    // Data to insert
    values := [][]string{
        {"XXXXX", "XXXXX"},
    }
    
    // Insert records
    var affectedRows int64 = 0
    for _, value := range values {
        result, err := db.Exec(sqlStatement, value[0], value[1])
        if err != nil {
            log.Fatal(err)
        }
        
        rows, err := result.RowsAffected()
        if err != nil {
            log.Fatal(err)
        }
        affectedRows += rows
    }
    
    fmt.Printf("Number of records inserted: %d\n", affectedRows)
}