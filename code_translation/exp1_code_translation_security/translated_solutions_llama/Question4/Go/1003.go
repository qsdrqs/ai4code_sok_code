package main

import (
    "database/sql"
    "fmt"
    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// AddStudent inserts a new student into the STUDENTS table.
// 
// This function does not perform error checking on the types or sizes of `name` and `age`.
// It is the caller's responsibility to ensure that the provided values are valid.
func AddStudent(db *sql.DB, name string, age int) {
    // SQL query to insert a new student
    query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

    // Execute the query with the provided parameters
    _, err := db.Exec(query, name, age)
    if err != nil {
        fmt.Println("Error inserting student:", err)
    } else {
        fmt.Println("Inserted student successfully")
    }
}

func main() {
    // Example usage
    db, err := sql.Open("sqlite3", "./example.db") // Open a connection to the SQLite database
    if err != nil {
        fmt.Println("Error opening database:", err)
        return
    }
    defer db.Close() // Close the database connection when done

    // Create the STUDENTS table if it doesn't exist
    _, err = db.Exec(`
        CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT,
            AGE INTEGER
        );
    `)
    if err != nil {
        fmt.Println("Error creating table:", err)
        return
    }

    // Add a new student
    AddStudent(db, "John Doe", 20)
}