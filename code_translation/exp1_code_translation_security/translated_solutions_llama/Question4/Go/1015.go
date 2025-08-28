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
func AddStudent(name string, age int) error {
    // Database connection settings
    dbConfig := map[string]string{
        "host":     "localhost",
        "user":     "yourusername",
        "password": "yourpassword",
        "database": "mydb",
    }

    // Create a DSN (Data Source Name) string
    dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s",
        dbConfig["user"],
        dbConfig["password"],
        dbConfig["host"],
        dbConfig["database"],
    )

    // Open a connection to the database
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        return err
    }
    defer db.Close()

    // Ping the database to verify the connection
    err = db.Ping()
    if err != nil {
        return err
    }

    // Prepare the SQL query
    sqlQuery := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

    // Execute the query with the provided student data
    _, err = db.Exec(sqlQuery, name, age)
    if err != nil {
        return err
    }

    fmt.Println("1 record inserted")
    return nil
}

func main() {
    // Example usage
    err := AddStudent("John Doe", 20)
    if err != nil {
        fmt.Println(err)
    }
}