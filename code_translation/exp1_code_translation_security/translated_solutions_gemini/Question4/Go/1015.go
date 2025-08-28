package main

import (
	// Standard library packages
	"database/sql"
	"fmt"
	"log"

	// The Go MySQL driver. The blank identifier `_` is used because
	// the driver is registered with the `database/sql` package via an `init()`
	// function, and we don't need to call any of its functions directly.
	_ "github.com/go-sql-driver/mysql"
)

// AddStudent connects to a MySQL database and inserts a new student record.
// It returns an error if any step of the process fails.
func AddStudent(name string, age int) error {
	// 1. Define the database connection string (Data Source Name).
	// Format: user:password@tcp(host:port)/dbname
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/mydb"

	// 2. Open a connection to the database.
	// sql.Open() doesn't actually create a connection, but prepares a DB object.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		// If opening fails, return the error.
		return fmt.Errorf("failed to open database connection: %w", err)
	}
	// `defer db.Close()` ensures the connection is closed when the function exits.
	defer db.Close()

	// 3. Ping the database to verify the connection is alive.
	// This is the first time a real connection is established.
	err = db.Ping()
	if err != nil {
		return fmt.Errorf("failed to connect to database: %w", err)
	}
	fmt.Println("Connected!")

	// 4. Prepare the SQL statement.
	// Using `?` as placeholders prevents SQL injection attacks.
	// This is a critical security improvement over the original JavaScript code.
	sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

	// 5. Execute the statement with the provided name and age.
	// The database driver safely substitutes the `?` placeholders with the arguments.
	result, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		return fmt.Errorf("failed to execute insert statement: %w", err)
	}

	// 6. Check how many rows were affected.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	fmt.Printf("%d record(s) inserted.\n", rowsAffected)
	return nil // Return nil to indicate success
}

// main function to demonstrate how to use AddStudent
func main() {
	// Example usage of the AddStudent function.
	err := AddStudent("Alice", 22)
	if err != nil {
		// log.Fatal will print the error and exit the program.
		log.Fatalf("Error adding student: %v", err)
	}

	err = AddStudent("Bob", 25)
	if err != nil {
		log.Fatalf("Error adding student: %v", err)
	}
}