package main

import (
    "database/sql"
    "fmt"

    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// Student represents a student with a name and age
type Student struct {
    Name string
    Age  int
}

// AddStudent adds a new student to the database
func AddStudent(db *sql.DB, name string, age int) error {
    query := "INSERT INTO STUDENTS (name, age) VALUES (?, ?)"
    _, err := db.Exec(query, name, age)
    return err
}

func main() {
    // Define database connection parameters
    dbConfig := map[string]string{
        "user":   "your_username",
        "password": "your_password",
        "host":   "your_host",
        "port":   "your_port",
        "db":     "your_database",
    }

    // Construct the DSN (Data Source Name) string
    dsn := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s",
        dbConfig["user"],
        dbConfig["password"],
        dbConfig["host"],
        dbConfig["port"],
        dbConfig["db"])

    // Open a connection to the database
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        panic(err)
    }
    defer db.Close()

    // Ping the database to verify the connection
    err = db.Ping()
    if err != nil {
        panic(err)
    }

    // Example usage: add a new student
    name := "John Doe"
    age := 20
    err = AddStudent(db, name, age)
    if err != nil {
        panic(err)
    }
    fmt.Printf("Added student: %s, %d\n", name, age)
}