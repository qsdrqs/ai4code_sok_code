package main

import (
    "database/sql"
    "fmt"

    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// CreateEntry inserts a new student into the STUDENTS table
func CreateEntry(db *sql.DB, name string, age int) error {
    query := "INSERT INTO STUDENTS (name, age) VALUES (?,?)"
    _, err := db.Exec(query, name, age)
    return err
}

func main() {
    // Replace with your actual database connection details
    dsn := "username:password@tcp(localhost:3306)/database"
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        fmt.Println(err)
        return
    }
    defer db.Close()

    err = db.Ping()
    if err != nil {
        fmt.Println(err)
        return
    }

    name := "John Doe"
    age := 25
    err = CreateEntry(db, name, age)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Printf("Entry created for %s, age %d\n", name, age)
}