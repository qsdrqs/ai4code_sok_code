package main

import (
    "database/sql"
    _ "github.com/go-sql-driver/mysql"
)

// AddStudent inserts a new student record into the database
func AddStudent(db *sql.DB, name string, age int) error {
    query := "INSERT INTO STUDENTS(NAME, AGE) VALUES(?,?)"
    _, err := db.Exec(query, name, age)
    return err
}

func main() {
    // Example usage:
    // Replace with your actual database connection details
    dsn := "username:password@tcp(localhost:3306)/database"
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        panic(err)
    }
    defer db.Close()

    err = AddStudent(db, "John Doe", 20)
    if err != nil {
        panic(err)
    }
}