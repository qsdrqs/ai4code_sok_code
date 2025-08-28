package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // Example: MySQL driver
	// _ "github.com/lib/pq" // Example: PostgreSQL driver
	// _ "github.com/mattn/go-sqlite3" // Example: SQLite driver
)

// addStudentSecure safely inserts a new student into the database.
// It takes a database connection pool (*sql.DB) as an argument.
func addStudentSecure(db *sql.DB, name string, age int) error {
	// The '?' is a placeholder for a parameter. This prevents SQL injection.
	// Note: The placeholder character may vary by database driver (e.g., '$1' for PostgreSQL).
	command := "INSERT INTO STUDENTS (name, age) VALUES (?, ?)"

	// Exec executes a command without returning any rows.
	// The arguments 'name' and 'age' are passed safely to the database driver.
	_, err := db.Exec(command, name, age)
	if err != nil {
		log.Printf("Failed to execute insert command: %v", err)
		return err
	}

	log.Printf("Successfully added student: %s, Age: %d", name, age)
	return nil
}

func main() {
	// This part is for demonstration. In a real app, the database connection
	// would be initialized once and shared.
	// We are using an in-memory SQLite database for this example.
	// To run this, execute: go get github.com/mattn/go-sqlite3
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatalf("Failed to open database: %v", err)
	}
	defer db.Close()

	// Create the table for the demonstration
	_, err = db.Exec("CREATE TABLE STUDENTS (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)")
	if err != nil {
		log.Fatalf("Failed to create table: %v", err)
	}

	fmt.Println("\n--- Running idiomatic and secure version ---")
	// Call the secure function
	err = addStudentSecure(db, "Bob", 42)
	if err != nil {
		log.Println("Error adding Bob.")
	}

	// This demonstrates the vulnerability of the first method
	// A malicious user could inject SQL to drop the table
	maliciousName := "Robert'); DROP TABLE STUDENTS; --"
	fmt.Printf("\nAttempting to add a student with a malicious name: %s\n", maliciousName)
	// We can't run the first function here as it uses mock objects, but if it were
	// connected to a real database, the STUDENTS table would be deleted.
	// The secure function, however, would treat the malicious string as just a name.
	err = addStudentSecure(db, maliciousName, 99)
	if err != nil {
		log.Println("Error adding malicious user (as expected with some DB constraints, but the DB is safe).")
	} else {
		log.Println("Malicious user added safely as a string, table was not dropped.")
	}
}