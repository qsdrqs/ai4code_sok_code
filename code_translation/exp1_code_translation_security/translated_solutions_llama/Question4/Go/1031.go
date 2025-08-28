package main

import (
    "database/sql"
    "fmt"
    _ "github.com/go-sql-driver/mysql"
    "log"
)

// Insert inserts a record into the students table
func Insert(name string, age int) {
    // Define database connection parameters
    const (
        host     = "localhost"
        user     = "yourusername"
        password = "yourpassword"
        database = "STUDENTS"
    )

    // Establish a connection to the database
    dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s", user, password, host, database)
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()

    // Ping the database to verify connection
    err = db.Ping()
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println("Connected!")

    // Prepare and execute the INSERT query
    query := "INSERT INTO students (name, age) VALUES (?, ?)"
    _, err = db.Exec(query, name, age)
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println("1 record inserted")
}

func main() {
    // Example usage
    Insert("John Doe", 25)
}