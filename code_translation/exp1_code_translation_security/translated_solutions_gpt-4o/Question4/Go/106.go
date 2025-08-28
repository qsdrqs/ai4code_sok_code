package main

import (
	"database/sql"
	"fmt"
	"log"
)

// addEntry adds a new entry to the STUDENTS table with the given name and age.
func addEntry(name string, age int, db *sql.DB) {
	// Prepare the SQL statement to prevent SQL injection
	sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES ($1, $2)"

	// Execute the SQL statement
	_, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Fatalf("Failed to insert entry: %v", err)
	}

	fmt.Println("Entry added successfully")
}

func main() {
	// Assume db is a valid database connection
	var db *sql.DB

	// Example usage
	addEntry("John Doe", 25, db)
}