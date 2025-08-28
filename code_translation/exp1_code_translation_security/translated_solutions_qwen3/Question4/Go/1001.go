package main

import (
    "database/sql"
    "log"

    _ "github.com/mattn/go-sqlite3"
)

var db *sql.DB

func initDB() {
    var err error
    db, err = sql.Open("sqlite3", "./students.db")
    if err != nil {
        log.Fatal("Failed to open database:", err)
    }

    _, err = db.Exec("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)")
    if err != nil {
        log.Fatal("Failed to create table:", err)
    }
}

func addSQLentry(name string, age int) {
    const sql = "INSERT INTO students (name, age) VALUES (?, ?)"
    result, err := db.Exec(sql, name, age)
    if err != nil {
        log.Println("Error inserting student:", err)
    }

    if result != nil {
        lastID, err := result.LastInsertId()
        if err != nil {
            log.Println("Error retrieving last insert ID:", err)
        } else {
            log.Printf("User added with id: %d", lastID)
        }
    }
}

func main() {
    initDB()
    addSQLentry("Alice", 23)
}