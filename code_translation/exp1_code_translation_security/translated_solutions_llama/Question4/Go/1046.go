package main

import (
    "database/sql"
    "fmt"

    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// Config represents the database configuration
type Config struct {
    Host     string
    Port     int
    Username string
    Password string
    Database string
}

// NewDBConnection creates a new database connection
func NewDBConnection(cfg Config) (*sql.DB, error) {
    dsn := fmt.Sprintf("%s:%s@tcp(%s:%d)/%s",
        cfg.Username, cfg.Password, cfg.Host, cfg.Port, cfg.Database)
    return sql.Open("mysql", dsn)
}

// InsertInto inserts data into the STUDENTS table
func InsertInto(db *sql.DB, name string, age int) error {
    query := "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)"
    _, err := db.Exec(query, name, age)
    return err
}

func main() {
    // Create a new database configuration
    cfg := Config{
        Host:     "localhost",
        Port:     3306,
        Username: "your_username",
        Password: "your_password",
        Database: "your_database",
    }

    // Create a new database connection
    db, err := NewDBConnection(cfg)
    if err != nil {
        fmt.Println(err)
        return
    }
    defer db.Close()

    // Insert data into the STUDENTS table
    name := "John Doe"
    age := 25
    err = InsertInto(db, name, age)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Println("Data inserted successfully")
}