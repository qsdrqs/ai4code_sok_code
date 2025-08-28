package main

import (
    "database/sql"
    "fmt"
)

// addEntry inserts a new student entry into the STUDENTS table.
// It constructs the SQL query by directly concatenating the name and age values.
// WARNING: This approach is vulnerable to SQL injection and should not be used in production.
func addEntry(name string, age int, db *sql.DB) {
    // Construct the SQL query string using string formatting
    query := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)

    // Execute the query, ignoring any returned error
    _, _ = db.Exec(query)
}