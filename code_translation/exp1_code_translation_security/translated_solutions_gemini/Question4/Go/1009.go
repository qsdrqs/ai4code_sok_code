package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import `_` is used for the MySQL driver.
	// Go's `database/sql` package requires a driver to be registered,
	// but the driver's functions are not called directly from user code.
	_ "github.com/go-sql-driver/mysql"
)

// createEntry inserts a new student record into the database.
// In Go, it's idiomatic to pass the database handle (*sql.DB) as an argument
// rather than relying on a global variable.
// The function also returns an `error` value to indicate success or failure.
func createEntry(db *sql.DB, name string, age int) error {
	// The SQL query uses '?' as a placeholder for parameters to prevent SQL injection.
	query := "INSERT INTO STUDENTS (name, age) VALUES (?, ?)"

	// db.Exec is used for statements that don't return rows (INSERT, UPDATE, DELETE).
	// The arguments `name` and `age` are passed safely to the driver.
	_, err := db.Exec(query, name, age)
	if err != nil {
		// If an error occurs, we return it to the caller.
		return fmt.Errorf("could not insert entry for %s: %w", name, err)
	}

	// A `nil` error indicates success.
	return nil
}

// main function to demonstrate usage
func main() {
	// 1. CONNECT TO THE DATABASE
	// The Data Source Name (DSN) contains the connection details.
	// Format: "user:password@tcp(host:port)/dbname"
	dsn := "your_user:your_password@tcp(127.0.0.1:3306)/your_database"

	// sql.Open() initializes a connection pool. It does not create a connection yet.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		// log.Fatal will print the error and exit the program.
		log.Fatal("Failed to open database connection:", err)
	}
	// Defer closing the database connection until the main function exits.
	defer db.Close()

	// 2. VERIFY THE CONNECTION
	// db.Ping() verifies that a connection to the database is still alive,
	// establishing a connection if necessary. This is the equivalent of the
	// `con.connect` callback in the JavaScript example.
	err = db.Ping()
	if err != nil {
		log.Fatal("Failed to connect to the database:", err)
	}

	fmt.Println("Successfully connected to the database!")

	// 3. CALL THE FUNCTION TO CREATE AN ENTRY
	// We call our function with the database handle and the data.
	err = createEntry(db, "Alice", 25)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Entry created successfully!")
}