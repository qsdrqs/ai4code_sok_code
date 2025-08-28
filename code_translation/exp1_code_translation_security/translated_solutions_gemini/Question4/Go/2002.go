package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import is used to register the MySQL driver.
	_ "github.com/go-sql-driver/mysql"
)

// AddStudent inserts a new student record into the database.
// In Go, it's idiomatic to pass dependencies like the database connection
// as arguments to the function.
// The function returns an error to allow the caller to handle failures.
func AddStudent(db *sql.DB, name string, age int) error {
	// The SQL query with '?' placeholders is the same.
	// Using backticks for multi-line strings is common in Go.
	query := `INSERT INTO STUDENTS(NAME, AGE)
              VALUES(?, ?)`

	// db.Exec is used for statements that don't return rows (e.g., INSERT, UPDATE, DELETE).
	// It safely passes the arguments to the database driver, preventing SQL injection.
	// The first return value (sql.Result) is ignored with '_' because we don't need it here.
	_, err := db.Exec(query, name, age)
	if err != nil {
		// If an error occurs, we return it to the caller.
		return fmt.Errorf("failed to add student: %w", err)
	}

	// A nil error indicates success.
	return nil
}

// --- Example Usage ---
// The following main function demonstrates how to use AddStudent.
func main() {
	// In a real application, the connection string would come from a config file or environment variable.
	// Format: "user:password@tcp(host:port)/dbname"
	dsn := "root:password@tcp(127.0.0.1:3306)/school_db"

	// sql.Open() initializes a connection pool.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Failed to open database connection: %v", err)
	}
	// defer db.Close() ensures the connection is closed when the main function exits.
	defer db.Close()

	// Ping the database to verify the connection is alive.
	err = db.Ping()
	if err != nil {
		log.Fatalf("Failed to connect to the database: %v", err)
	}

	fmt.Println("Successfully connected to the database!")

	// Call the function to add a new student.
	studentName := "Alice"
	studentAge := 22
	err = AddStudent(db, studentName, studentAge)
	if err != nil {
		// Handle the error returned by AddStudent.
		log.Fatalf("Error executing query: %v", err)
	}

	fmt.Printf("Successfully added student: %s\n", studentName)
}