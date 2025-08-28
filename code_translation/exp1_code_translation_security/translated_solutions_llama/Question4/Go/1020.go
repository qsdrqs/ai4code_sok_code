package main

import (
    "database/sql"
    "fmt"
    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// Inserter inserts a record into the STUDENTS table
func inserter(name string, age int) {
    // Database connection settings
    const (
        host     = "localhost"
        user     = "yourusername"
        password = "yourpassword"
        database = "mydb"
    )

    // Create a DSN (Data Source Name) string
    dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s", user, password, host, database)

    // Open a connection to the database
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        panic(err)
    }
    defer db.Close()

    // Ping the database to verify the connection
    err = db.Ping()
    if err != nil {
        panic(err)
    }
    fmt.Println("Connected!")

    // Prepare and execute the INSERT query
    query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    _, err = db.Exec(query, name, age)
    if err != nil {
        panic(err)
    }
    fmt.Println("1 record inserted")
}

func main() {
    inserter("John Doe", 25)
}