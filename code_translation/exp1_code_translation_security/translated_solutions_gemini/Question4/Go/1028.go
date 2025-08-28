package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank identifier _ is used here because the driver is registered
	// in its init() function and we only need the side effect of its
	// registration. We don't use any functions from the driver package directly.
	// You can replace this with the driver for your specific database (e.g., "github.com/lib/pq" for PostgreSQL).
	_ "github.com/go-sql-driver/mysql"
)

// addStudent inserts a new student record into the database.
// It takes a database connection pool (*sql.DB), name, and age as arguments.
// It returns an error if the insertion fails.
func addStudent(db *sql.DB, name string, age int) error {
	// The `?` is a placeholder for a parameter. This prevents SQL injection.
	// Note: Some databases like PostgreSQL use a different placeholder, e.g., $1, $2.
	sqlStatement := `INSERT INTO STUDENTS (name, age) VALUES (?, ?)`

	// Exec executes a query without returning any rows.
	// The arguments following the query string are substituted for the placeholders.
	_, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		// If there's an error, return it to the caller.
		return err
	}

	fmt.Println("1 record inserted")
	return nil // Return nil on success
}

// Example of how to use the addStudent function
func main() {
	// sql.Open() doesn't create a connection immediately. It prepares a DB object
	// for later use. The actual connection is established lazily.
	// Replace the connection string with your actual database credentials.
	// Format: "user:password@tcp(127.0.0.1:3306)/database_name"
	db, err := sql.Open("mysql", "user:password@tcp(127.0.0.1:3306)/school")
	if err != nil {
		log.Fatal(err) // log.Fatal prints the error and exits.
	}
	// Defer closing the database connection until the main function exits.
	defer db.Close()

	// Ping verifies a connection to the database is still alive,
	// establishing a connection if necessary.
	err = db.Ping()
	if err != nil {
		log.Fatal("Failed to connect to the database:", err)
	}

	fmt.Println("Successfully connected to the database!")

	// Now, let's call our function to add a student.
	err = addStudent(db, "Alice", 22)
	if err != nil {
		log.Fatalf("Failed to add student: %v", err)
	}

	err = addStudent(db, "Bob", 25)
	if err != nil {
		log.Fatalf("Failed to add student: %v", err)
	}
}