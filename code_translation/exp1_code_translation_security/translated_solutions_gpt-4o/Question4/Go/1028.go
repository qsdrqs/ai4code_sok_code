package main

import (
    "database/sql"
    "fmt"
    "log"

    _ "github.com/go-sql-driver/mysql"
)

func addStudent(db *sql.DB, name string, age int) {
    sql := "INSERT INTO STUDENTS (name, age) VALUES (?, ?)"
    result, err := db.Exec(sql, name, age)
    if err != nil {
        log.Fatal(err)
    }

    rowsAffected, err := result.RowsAffected()
    if err != nil {
        log.Fatal(err)
    }

    fmt.Printf("%d record(s) inserted\n", rowsAffected)
}

func main() {
    // Replace with your own connection string
    dsn := "user:password@tcp(127.0.0.1:3306)/dbname"
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()

    // Example usage
    addStudent(db, "John Doe", 20)
}