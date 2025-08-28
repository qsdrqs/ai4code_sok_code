package main

import (
	"database/sql"
	"fmt"
	"log"

	// The underscore `_` is used to import the driver for its side effects
	// (registering itself with the `database/sql` package) without needing
	// to directly reference any of its exported names.
	_ "github.com/go-sql-driver/mysql"
)

// In a real application, this configuration would come from a config file or environment variables.
// The format is: "user:password@tcp(host:port)/dbname"
const dsn = "user:password@tcp(127.0.0.1:3306)/your_database_name"

// InsertInto inserts a new student record into the STUDENTS table.
// It takes a database connection pool, name, and age as arguments.
// It returns an error if the insertion fails.
func InsertInto(db *sql.DB, name string, age int) error {
	// 1. Define the SQL statement with placeholders (?) to prevent SQL injection.
	sqlStatement := `INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)`

	// 2. Execute the statement, passing the actual values as arguments.
	// The database driver will safely sanitize the inputs.
	// We use Exec for statements that don't return rows (INSERT, UPDATE, DELETE).
	_, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		// If there's an error, wrap it with more context and return it.
		return fmt.Errorf("failed to insert student %s: %w", name, err)
	}

	// 3. Return nil if the execution was successful.
	return nil
}

func main() {
	// Establish a connection to the database.
	// sql.Open doesn't actually create a connection, but prepares a connection pool (*sql.DB).
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Failed to prepare database connection: %v", err)
	}
	// defer db.Close() ensures the connection pool is closed when main() exits.
	defer db.Close()

	// It's a good practice to Ping the database to verify the connection is alive.
	err = db.Ping()
	if err != nil {
		log.Fatalf("Failed to connect to the database: %v", err)
	}

	fmt.Println("Successfully connected to the database!")

	// --- Example Usage ---
	// Call the function to insert a new student.
	studentName := "Alice"
	studentAge := 25

	err = InsertInto(db, studentName, studentAge)
	if err != nil {
		log.Printf("Error inserting record: %v", err)
	} else {
		fmt.Printf("Successfully inserted student: %s, Age: %d\n", studentName, studentAge)
	}
}