package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// Assuming you have a global database connection
var db *sql.DB

func addStudent(name string, age int) {
    sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
    
    _, err := db.Exec(sql)
    if err != nil {
        log.Fatal(err) // equivalent to "throw err"
    }
    
    fmt.Println("1 record inserted")
}

// Example of how to initialize the database connection
func initDB() {
    var err error
    // Replace with your actual database connection string
    db, err = sql.Open("mysql", "username:password@tcp(localhost:3306)/database_name")
    if err != nil {
        log.Fatal(err)
    }
    
    err = db.Ping()
    if err != nil {
        log.Fatal(err)
    }
}

func main() {
    initDB()
    defer db.Close()
    
    // Example usage
    addStudent("John Doe", 20)
}