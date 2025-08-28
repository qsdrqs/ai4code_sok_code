package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

func insert(name string, age int) {
    // Create database connection
    db, err := sql.Open("mysql", "yourusername:yourpassword@tcp(localhost:3306)/STUDENTS")
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

    // Prepare and execute the SQL statement
    sql := "INSERT INTO students (name, age) VALUES (?, ?)"
    _, err = db.Exec(sql, name, age)
    if err != nil {
        log.Fatal(err)
    }
    
    fmt.Println("1 record inserted")
}

func main() {
    // Example usage
    insert("John Doe", 25)
}