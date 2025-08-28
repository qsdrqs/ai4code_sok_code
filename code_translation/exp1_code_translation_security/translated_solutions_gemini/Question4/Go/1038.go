package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank identifier `_` is used because we only need to import this
	// package for its side effects (registering the mysql driver). We won't
	// be calling any of its functions directly.
	_ "github.com/go-sql-driver/mysql"
)

// Insertion inserts a new student record into the database.
// It takes a name (string) and age (int) as arguments.
// It returns an error if the operation fails, otherwise it returns nil.
func Insertion(name string, age int) error {
	// 1. Set up the database connection.
	// The Data Source Name (DSN) format is: user:password@tcp(host:port)/dbname
	// IMPORTANT: Replace the placeholder values with your actual database credentials.
	dsn := "your_user:your_password@tcp(127.0.0.1:3306)/your_database"

	// sql.Open() prepares a database connection pool. It does not connect immediately.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		// If the DSN is malformed, an error is returned here.
		return fmt.Errorf("failed to open database: %w", err)
	}
	// defer ensures that the connection is closed when the function exits.
	defer db.Close()

	// 2. Verify the connection is alive.
	// db.Ping() sends a ping to the database to ensure the connection is valid.
	err = db.Ping()
	if err != nil {
		return fmt.Errorf("failed to connect to database: %w", err)
	}

	fmt.Println("Successfully connected to the database!")

	// 3. Prepare the SQL statement for insertion.
	// Using `?` as a placeholder is crucial to prevent SQL injection attacks.
	// The database driver will safely substitute the values.
	sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

	// 4. Execute the SQL statement.
	// db.Exec() is used for statements that don't return rows (INSERT, UPDATE, DELETE).
	_, err = db.Exec(sqlStatement, name, age)
	if err != nil {
		return fmt.Errorf("failed to execute insertion: %w", err)
	}

	// If we reach here, the insertion was successful.
	return nil
}

// main function to demonstrate how to use the Insertion function.
func main() {
	// Example usage:
	studentName := "Alice"
	studentAge := 25

	err := Insertion(studentName, studentAge)
	if err != nil {
		// log.Fatal will print the error and exit the program.
		log.Fatalf("Error inserting record: %v", err)
	}

	fmt.Printf("Successfully inserted student: %s, Age: %d\n", studentName, studentAge)
}