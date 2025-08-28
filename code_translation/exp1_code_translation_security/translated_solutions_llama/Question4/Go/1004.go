package main

import (
    "database/sql"
    "fmt"
    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// addStudent generates SQL query to add a student to the STUDENTS table
func addStudent(name string, age int) string {
    sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
    fmt.Println(sql)
    return sql
}

func main() {
    // Database connection settings
    const (
        host     = "localhost"
        user     = "yourusername"
        password = "yourpassword"
        dbName   = "yourdbname" // Specify your database name
    )

    // DSN (Data Source Name) string
    dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s", user, password, host, dbName)

    // Open a connection to the database
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
    fmt.Println("Connected!")

    // Prepare and execute the query
    query := addStudent("XXXXX", 0)
    result, err := db.Exec(query)
    if err != nil {
        panic(err)
    }

    // Print the result
    fmt.Println("Result:", result)
}