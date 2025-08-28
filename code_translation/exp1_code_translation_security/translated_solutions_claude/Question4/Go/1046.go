package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

// Database configuration - replace with your actual database credentials
type Config struct {
    Host     string
    Port     string
    User     string
    Password string
    Database string
}

// Example configuration - modify according to your database setup
var config = Config{
    Host:     "localhost",
    Port:     "3306",
    User:     "your_username",
    Password: "your_password",
    Database: "your_database",
}

// Create database connection
func createConnection() (*sql.DB, error) {
    dsn := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s", 
        config.User, config.Password, config.Host, config.Port, config.Database)
    
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        return nil, err
    }
    
    // Test the connection
    if err := db.Ping(); err != nil {
        return nil, err
    }
    
    return db, nil
}

func insertInto(name string, age int) error {
    // Create connection
    connection, err := createConnection()
    if err != nil {
        return fmt.Errorf("failed to create connection: %v", err)
    }
    defer connection.Close() // Equivalent to connection.end() in JavaScript
    
    // Prepare SQL statement with placeholders to prevent SQL injection
    sql := "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)"
    
    // Execute the query
    _, err = connection.Exec(sql, name, age)
    if err != nil {
        return fmt.Errorf("failed to execute query: %v", err)
    }
    
    return nil
}

// Example usage
func main() {
    err := insertInto("John Doe", 20)
    if err != nil {
        log.Fatal("Error inserting data:", err)
    }
    fmt.Println("Data inserted successfully!")
}