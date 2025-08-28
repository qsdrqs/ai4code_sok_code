package main

import (
	"database/sql"
	"log"

	// The blank identifier _ is used because we only need to import this
	// package for its side effect of registering the sqlite3 driver.
	_ "github.com/mattn/go-sqlite3"
)

// AddSQLEntry inserts a new student record into the database.
// It takes a database connection, a name, and an age as arguments.
// It returns the ID of the newly inserted row and an error if one occurred.
func AddSQLEntry(db *sql.DB, name string, age int) (int64, error) {
	// The SQL statement to be executed. '?' is the placeholder for parameters.
	sqlStatement := `INSERT INTO students (name, age) VALUES (?, ?)`

	// db.Exec is used for statements that don't return rows (e.g., INSERT, UPDATE, DELETE).
	// It prepares the statement, executes it with the provided parameters, and then closes the statement.
	result, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Printf("Error executing statement: %v", err)
		return 0, err
	}

	// LastInsertId returns the integer ID of the last row inserted.
	// Not all database drivers support this.
	id, err := result.LastInsertId()
	if err != nil {
		log.Printf("Error getting last insert ID: %v", err)
		return 0, err
	}

	log.Printf("User added with id: %d", id)
	return id, nil
}

// Example of how to use the function
func main() {
	// Open a connection to an in-memory SQLite database.
	// For a file, use sql.Open("sqlite3", "./your_database.db")
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatalf("Failed to open database: %v", err)
	}
	// Defer closing the database connection until the main function exits.
	defer db.Close()

	// Create the students table for the example to work.
	createTableSQL := `CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER);`
	_, err = db.Exec(createTableSQL)
	if err != nil {
		log.Fatalf("Failed to create table: %v", err)
	}

	// Call the function to add a new student.
	_, err = AddSQLEntry(db, "Alice", 21)
	if err != nil {
		// The error is already logged inside AddSQLEntry,
		// but we could add more handling here if needed.
		log.Println("Failed to add Alice.")
	}

	_, err = AddSQLEntry(db, "Bob", 22)
	if err != nil {
		log.Println("Failed to add Bob.")
	}
}