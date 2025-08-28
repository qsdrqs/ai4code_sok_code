package main

import (
    "database/sql"
    "fmt"

    _ "github.com/go-sql-driver/mysql"
)

// Student represents a student
type Student struct {
    Name string
    Age  int
}

func main() {
    // Define database connection parameters
    const (
        host     = "localhost"
        username = "username"
        password = "password"
        database = "database"
    )

    // Establish a database connection
    dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s", username, password, host, database)
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        panic(err)
    }
    defer db.Close()

    // Insert a new student
    name := "John Doe"
    age := 25
    insertStudent(db, name, age)
}

func insertStudent(db *sql.DB, name string, age int) {
    // SQL query to insert a new student
    query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

    // Prepare and execute the SQL query
    stmt, err := db.Prepare(query)
    if err != nil {
        panic(err)
    }
    defer stmt.Close()

    _, err = stmt.Exec(name, age)
    if err != nil {
        panic(err)
    }

    fmt.Println("1 record inserted")
}