package main

import (
    "database/sql"
    "fmt"
    _ "github.com/go-sql-driver/mysql" // MySQL driver
    "log"
)

// Config holds database configuration
type Config struct {
    Host     string
    Port     int
    Username string
    Password string
    Database string
}

// Add inserts a new todo into the database
func Add(name string, age int, table string) {
    // Database configuration
    config := Config{
        Host:     "localhost",
        Port:     3306,
        Username: "username",
        Password: "password",
        Database: "database",
    }

    // Connection string
    connectionString := fmt.Sprintf("%s:%s@tcp(%s:%d)/%s",
        config.Username, config.Password, config.Host, config.Port, config.Database)

    // Open database connection
    db, err := sql.Open("mysql", connectionString)
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()

    // Prepare SQL statement
    stmt := `INSERT INTO todos(title, completed) VALUES (?)`

    // Execute SQL statement
    _, err = db.Exec(stmt, name)
    if err != nil {
        log.Fatal(err)
    }

    // Get inserted rows
    result, err := db.Exec(stmt, name)
    if err != nil {
        log.Fatal(err)
    }
    rowsAffected, err := result.RowsAffected()
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println("Row inserted:", rowsAffected)
}

func main() {
    Add("New Todo", 0, "todos")
}