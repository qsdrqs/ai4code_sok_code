package main

import (
	"context"
	"database/sql"
	"log"
	"time"

	// The blank import is used to register the mysql driver.
	// You can replace this with the driver for your database (e.g., postgres, sqlite3).
	_ "github.com/go-sql-driver/mysql"
)

// add securely inserts a new entry into the STUDENTS table using a prepared statement.
// It takes a database connection pool (*sql.DB) and the student's details.
func add(db *sql.DB, name string, age int) error {
	// The SQL query uses '?' as a placeholder for parameters. This prevents SQL injection.
	// Note: Some databases like PostgreSQL use '$1', '$2' instead of '?'.
	query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
	log.Println("Preparing query:", query)

	// Create a context with a timeout to prevent the operation from hanging indefinitely.
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	// db.ExecContext is the recommended way to execute a command that doesn't return rows.
	// It safely passes the arguments `name` and `age` to the database driver.
	result, err := db.ExecContext(ctx, query, name, age)
	if err != nil {
		// Return the error to the caller for proper handling.
		return err
	}

	// Optionally, you can check how many rows were affected.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return err
	}

	log.Printf("%d record(s) inserted successfully.", rowsAffected)
	return nil
}

func main() {
	// The Data Source Name (DSN) for connecting to the database.
	// Format: user:password@tcp(127.0.0.1:3306)/database_name
	// IMPORTANT: Replace with your actual database connection details.
	dsn := "user:password@tcp(127.0.0.1:3306)/testdb?parseTime=true"
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatal(err)
	}
	// `defer db.Close()` ensures the connection is closed when main() exits.
	defer db.Close()

	// It's a good practice to ping the database to verify the connection.
	err = db.Ping()
	if err != nil {
		log.Fatal("Failed to connect to the database:", err)
	}

	log.Println("Successfully connected to the database!")

	// Call the function to add a new student and handle any potential error.
	if err := add(db, "Bob", 25); err != nil {
		log.Fatalf("Failed to add record: %v", err)
	}
}