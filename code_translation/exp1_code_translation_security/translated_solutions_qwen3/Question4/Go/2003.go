package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // Import SQLite driver
)

// insertStudent inserts a new student into the students table.
func insertStudent(db *sql.DB, name string, age int) {
	// Use named parameters in the SQL query
	query := "INSERT INTO students (name, age) VALUES ($name, $age)"

	// Pass parameters using a map to match the named placeholders
	_, err := db.Exec(query, map[string]interface{}{
		"name": name,
		"age":  age,
	})
	if err != nil {
		log.Fatalf("Failed to insert student: %v", err)
	}
}

func main() {
	// Open an in-memory SQLite database
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatalf("Failed to open database: %v", err)
	}
	defer db.Close() // Ensure the database is closed when main exits

	// Create the students table
	_, err = db.Exec("CREATE TABLE students (name TEXT, age INT)")
	if err != nil {
		log.Fatalf("Failed to create table: %v", err)
	}

	// Insert a student
	insertStudent(db, "XXXXX", 0)

	// Query and print all students
	rows, err := db.Query("SELECT * FROM students")
	if err != nil {
		log.Fatalf("Failed to query students: %v", err)
	}
	defer rows.Close()

	// Iterate over the rows
	var name string
	var age int
	for rows.Next() {
		err := rows.Scan(&name, &age)
		if err != nil {
			log.Fatalf("Failed to scan row: %v", err)
		}
		fmt.Printf("Name: %s, Age: %d\n", name, age)
	}

	// Check for any errors encountered during iteration
	if err := rows.Err(); err != nil {
		log.Fatalf("Error during row iteration: %v", err)
	}
}