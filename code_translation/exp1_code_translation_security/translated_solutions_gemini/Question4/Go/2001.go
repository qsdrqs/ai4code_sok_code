package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import `_` is used for the MySQL driver.
	// We need it for its side effect of registering itself with the `database/sql` package,
	// but we don't use any of its exported names directly in our code.
	_ "github.com/go-sql-driver/mysql"
)

// add inserts a new student record into the database.
// It takes a database connection pool (*sql.DB), a name, and an age as arguments.
// It returns an error if the database operation fails, otherwise it returns nil.
func add(db *sql.DB, name string, age int) error {
	// The `?` is the placeholder for query parameters, which prevents SQL injection.
	query := "INSERT INTO students (name, age) VALUES (?, ?)"

	// db.Exec is used for statements that don't return rows (INSERT, UPDATE, DELETE).
	// It prepares the statement, executes it with the provided arguments, and then closes the statement.
	result, err := db.Exec(query, name, age)
	if err != nil {
		// If there's an error, return it to the caller.
		return fmt.Errorf("could not insert student %s: %w", name, err)
	}

	// Optionally, you can check how many rows were affected.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("could not get rows affected: %w", err)
	}

	fmt.Printf("Added %s to students table (%d row affected)\n", name, rowsAffected)
	return nil // Return nil on success
}

func main() {
	// DSN: Data Source Name
	// Replace with your actual MySQL connection details.
	// Format: "user:password@tcp(host:port)/dbname"
	dsn := "user:password@tcp(127.0.0.1:3306)/your_database"

	// sql.Open() initializes a connection pool. It doesn't create a connection yet.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Failed to open database connection: %v", err)
	}
	// defer db.Close() ensures the connection is closed when main() exits.
	defer db.Close()

	// db.Ping() verifies that a connection to the database is still alive,
	// establishing a connection if necessary.
	err = db.Ping()
	if err != nil {
		log.Fatalf("Failed to connect to database: %v", err)
	}

	fmt.Println("Successfully connected to MySQL database!")

	// --- Calling the translated function ---

	// Example 1: Add a new student named "Alice"
	if err := add(db, "Alice", 22); err != nil {
		log.Printf("Error adding student: %v", err)
	}

	// Example 2: Add another student named "Bob"
	if err := add(db, "Bob", 25); err != nil {
		log.Printf("Error adding student: %v", err)
	}
}