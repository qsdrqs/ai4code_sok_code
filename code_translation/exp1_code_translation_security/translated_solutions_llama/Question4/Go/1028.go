package main

import (
    "database/sql"
    "fmt"
    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// AddStudent inserts a new student into the database
func AddStudent(db *sql.DB, name string, age int) error {
    // SQL query to insert a new student
    sqlQuery := "INSERT INTO STUDENTS (name, age) VALUES (?, ?)"

    // Prepare and execute the SQL query
    _, err := db.Exec(sqlQuery, name, age)
    if err != nil {
        return err
    }

    fmt.Println("1 record inserted")
    return nil
}

func main() {
    // Replace with your actual database connection details
    db, err := sql.Open("mysql", "username:password@tcp(localhost:3306)/database")
    if err != nil {
        panic(err)
    }
    defer db.Close()

    // Ping the database to verify connection
    err = db.Ping()
    if err != nil {
        panic(err)
    }

    // Example usage
    err = AddStudent(db, "John Doe", 25)
    if err != nil {
        panic(err)
    }
}