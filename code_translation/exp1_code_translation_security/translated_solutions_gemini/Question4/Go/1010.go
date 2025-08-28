package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // The sqlite3 driver
)

// The SQL table is named “STUDENTS” and contains columns for “NAME” (TEXT) and “AGE” (INT).

// addStudent takes a database connection, a name, and an age,
// and adds a new entry to the "STUDENTS" table.
// It returns the number of rows affected and any error that occurred.
func addStudent(db *sql.DB, name string, age int) (int64, error) {
	// The `?` are placeholders for parameters. This prevents SQL injection.
	sqlStatement := `INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)`

	// Exec executes a query without returning any rows.
	// It's used for INSERT, UPDATE, DELETE, etc.
	result, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		return 0, fmt.Errorf("failed to execute statement: %w", err)
	}

	// Get the number of rows affected by the query.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return 0, fmt.Errorf("failed to get rows affected: %w", err)
	}

	return rowsAffected, nil
}

// main function to test the addStudent function
func main() {
	// Open a connection to the SQLite database file.
	// It will be created if it doesn't exist.
	db, err := sql.Open("sqlite3", "./database.db")
	if err != nil {
		log.Fatalf("Failed to open database: %v", err)
	}
	// Defer closing the database to ensure it's cleaned up.
	defer db.Close()

	// --- One-time setup: Create the table ---
	// This should only be run once. You can comment it out after the first run.
	/*
		createTableSQL := `CREATE TABLE STUDENTS (NAME TEXT, AGE INT);`
		_, err = db.Exec(createTableSQL)
		if err != nil {
			log.Fatalf("Failed to create table: %v", err)
		}
		fmt.Println("STUDENTS table created successfully.")
	*/
	// --- End of one-time setup ---

	// Test example: Add a new student
	name := "John Doe"
	age := 21
	rowsAffected, err := addStudent(db, name, age)
	if err != nil {
		log.Fatalf("Error adding student: %v", err)
	}
	fmt.Printf("Successfully added student '%s'. Rows affected: %d\n", name, rowsAffected)

	// Another test example
	rowsAffected, err = addStudent(db, "Jane Smith", 23)
	if err != nil {
		log.Fatalf("Error adding student: %v", err)
	}
	fmt.Printf("Successfully added student '%s'. Rows affected: %d\n\n", "Jane Smith", rowsAffected)


	// Display the current contents of the database to verify
	fmt.Println("Current students in the database:")
	rows, err := db.Query("SELECT NAME, AGE FROM STUDENTS")
	if err != nil {
		log.Fatalf("Failed to query students: %v", err)
	}
	defer rows.Close()

	for rows.Next() {
		var currentName string
		var currentAge int
		if err := rows.Scan(&currentName, &currentAge); err != nil {
			log.Printf("Failed to scan row: %v", err)
			continue
		}
		fmt.Printf("- Name: %s, Age: %d\n", currentName, currentAge)
	}

	// Check for errors from iterating over rows.
	if err := rows.Err(); err != nil {
		log.Fatalf("Error during row iteration: %v", err)
	}
}