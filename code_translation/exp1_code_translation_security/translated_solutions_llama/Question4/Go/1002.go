package main

import (
    "database/sql"
    "fmt"

    _ "github.com/denisenkom/go-mssqldb"
)

// InsertUser inserts a new user into the STUDENTS table
func InsertUser(db *sql.DB, name string, age int) error {
    query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (@name, @age);"
    _, err := db.Exec(query, sql.Named("name", name), sql.Named("age", age))
    return err
}

func main() {
    // assume active conn
    // Replace with your actual connection string
    connStr := "server=localhost;user id=sa;password=P@ssw0rd;port=1433;database=your_database"
    db, err := sql.Open("mssql", connStr)
    if err != nil {
        fmt.Println(err)
        return
    }
    defer db.Close()

    err = InsertUser(db, "John Doe", 30)
    if err != nil {
        fmt.Println(err)
        return
    }
}