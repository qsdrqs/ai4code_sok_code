package main

import (
    "database/sql"
    "fmt"
    "log"

    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// MySQL configuration
const (
    host     = "localhost"
    user     = "yourusername"
    password = "yourpassword"
    database = "mydb"
)

// Student represents a student
type Student struct {
    Name string
    Age  int
}

func main() {
    // Connect to the database
    dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s", user, password, host, database)
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()

    // Ping the database to verify connection
    err = db.Ping()
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println("Connected!")

    // Get user input
    var name string
    fmt.Print("Enter a name: ")
    fmt.Scanln(&name)

    var age int
    fmt.Print("Enter an age: ")
    fmt.Scanln(&age)

    // Insert student into the database
    insertStudent(db, name, age)
}

func insertStudent(db *sql.DB, name string, age int) {
    // SQL query to insert a student
    sqlStmt := "INSERT INTO students (name, age) VALUES (?, ?)"

    // Execute the query
    _, err := db.Exec(sqlStmt, name, age)
    if err != nil {
        log.Fatal(err)
    }

    // Get the number of affected rows
    var affectedRows int64
    err = db.QueryRow("SELECT ROW_COUNT()").Scan(&affectedRows)
    if err != nil {
        log.Fatal(err)
    }

    fmt.Printf("Number of records inserted: %d\n", affectedRows)
}