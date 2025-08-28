package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

// Config structure to match the config.js file
type Config struct {
    Host     string
    User     string
    Password string
    Database string
    Port     string
}

// Mock config - replace with actual config values
var config = Config{
    Host:     "localhost",
    User:     "username",
    Password: "password",
    Database: "database_name",
    Port:     "3306",
}

func add(name string, age int, table string) {
    // Create connection string
    dsn := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s", 
        config.User, config.Password, config.Host, config.Port, config.Database)
    
    // Create database connection
    connection, err := sql.Open("mysql", dsn)
    if err != nil {
        log.Printf("Error connecting to database: %v", err)
        return
    }
    defer connection.Close()
    
    // Test the connection
    if err := connection.Ping(); err != nil {
        log.Printf("Error pinging database: %v", err)
        return
    }
    
    // Prepare the SQL statement
    stmt := "INSERT INTO todos(title, completed) VALUES (?, ?)"
    
    // Execute the query
    result, err := connection.Exec(stmt, name, age)
    if err != nil {
        log.Printf("Error executing query: %v", err)
        return
    }
    
    // Get affected rows
    affectedRows, err := result.RowsAffected()
    if err != nil {
        log.Printf("Error getting affected rows: %v", err)
        return
    }
    
    fmt.Printf("Row inserted: %d\n", affectedRows)
}

func main() {
    // Example usage
    add("Sample Task", 1, "todos")
}