package main

import (
    "database/sql"
    "fmt"

    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// MySQLConfig holds the configuration for the MySQL connection
type MySQLConfig struct {
    Host     string
    User     string
    Password string
    Database string
}

func main() {
    // MySQL configuration
    config := MySQLConfig{
        Host:     "127.0.0.1",
        User:     "yourusername",
        Password: "yourpassword",
        Database: "mydb",
    }

    // Connect to the database
    db, err := sql.Open("mysql", fmt.Sprintf("%s:%s@tcp(%s)/%s",
        config.User, config.Password, config.Host, config.Database))
    if err != nil {
        panic(err)
    }
    defer db.Close()

    // Ping the database to verify connection
    err = db.Ping()
    if err != nil {
        panic(err)
    }
    fmt.Println("Connected!")

    // SQL query
    sqlQuery := "INSERT INTO customers (name, address) VALUES (?, ?)"

    // Values to insert
    values := [][]interface{}{
        {"XXXXX", "XXXXX"},
    }

    // Execute the query
    stmt, err := db.Prepare(sqlQuery)
    if err != nil {
        panic(err)
    }
    defer stmt.Close()

    // Execute the query for each set of values
    for _, value := range values {
        _, err = stmt.Exec(value...)
        if err != nil {
            panic(err)
        }
    }

    // Alternatively, you can use db.Exec() with a single query and multiple values
    // sqlQuery := "INSERT INTO customers (name, address) VALUES (?, ?)"
    // _, err = db.Exec(sqlQuery, "XXXXX", "XXXXX")

    // Get the number of affected rows (Note: This will only give the count for the last query)
    // You may need to adjust this based on your use case
    fmt.Println("Number of records inserted: 1") // Assuming one insert operation
}