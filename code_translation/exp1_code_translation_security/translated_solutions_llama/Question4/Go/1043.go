package main

import (
    "database/sql"
    "fmt"
    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// AddStudent adds a new entry to the "STUDENTS" SQL table
func AddStudent(db *sql.DB, name string, age int) error {
    // SQL query to insert a new student
    sqlQuery := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

    // Log the SQL query
    fmt.Println(sqlQuery)

    // Prepare and execute the SQL query
    _, err := db.Exec(sqlQuery, name, age)
    if err != nil {
        return err
    }

    fmt.Println("1 record inserted")
    return nil
}

func main() {
    // Define database connection parameters
    dbUser := "your_username"
    dbPassword := "your_password"
    dbHost := "your_host"
    dbName := "your_database"

    // Establish a database connection
    dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s", dbUser, dbPassword, dbHost, dbName)
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        panic(err)
    }
    defer db.Close()

    // Ping the database to verify connection
    err = db.Ping()
    if err != nil {
        panic(err)
    }

    // Example usage: Add a new student
    name := "John Doe"
    age := 20
    err = AddStudent(db, name, age)
    if err != nil {
        panic(err)
    }
}