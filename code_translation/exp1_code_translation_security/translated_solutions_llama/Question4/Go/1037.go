package main

import (
    "database/sql"
    "fmt"

    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// MySQLConfig holds the MySQL connection configuration
type MySQLConfig struct {
    Host     string
    Port     int
    Username string
    Password string
    Database string
}

// InsertStudent inserts a new student record into the database
func insertStudent(config MySQLConfig, name string, age int) error {
    // Create a DSN (Data Source Name) string
    dsn := fmt.Sprintf("%s:%s@tcp(%s:%d)/%s",
        config.Username,
        config.Password,
        config.Host,
        config.Port,
        config.Database,
    )

    // Open a connection to the database
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        return err
    }
    defer db.Close()

    // Prepare the insert statement
    sqlStmt := "INSERT INTO STUDENTS(title, completed) VALUES('Learn how to insert a new row', true)"

    // Execute the insert statement
    _, err = db.Exec(sqlStmt)
    return err
}

func main() {
    // Define the MySQL connection configuration
    config := MySQLConfig{
        Host:     "localhost",
        Port:     3306,
        Username: "your_username",
        Password: "your_password",
        Database: "your_database",
    }

    // Insert a new student record
    err := insertStudent(config, "XXXXX", 0)
    if err != nil {
        fmt.Println(err)
    } else {
        fmt.Println("Student record inserted successfully")
    }
}