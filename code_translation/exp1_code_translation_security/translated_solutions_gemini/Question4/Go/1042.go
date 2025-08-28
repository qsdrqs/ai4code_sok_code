package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import `_` is used to register the driver with the `database/sql` package.
	// Replace with the driver for your specific database (e.g., "github.com/lib/pq" for PostgreSQL).
	_ "github.com/go-sql-driver/mysql"
)

// db is a global variable to hold the database connection pool.
// In a real application, you would initialize this in your main function
// and pass it to functions that need it, rather than using a global.
var db *sql.DB

// AddStudent inserts a new student record into the database.
// It takes a name and age as arguments and returns an error if one occurs.
func AddStudent(name string, age int) error {
	// The `?` is a placeholder for a parameter. This prevents SQL injection.
	// The database driver will safely substitute the `?` with the arguments provided.
	sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

	// db.Exec executes a query without returning any rows.
	// It's ideal for INSERT, UPDATE, or DELETE statements.
	result, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		// If there's an error, we return it to the caller.
		return fmt.Errorf("could not insert student: %w", err)
	}

	// You can optionally check how many rows were affected.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("could not get rows affected: %w", err)
	}

	fmt.Printf("%d record(s) inserted.\n", rowsAffected)
	return nil
}

func main() {
	// Example of how to initialize the database connection.
	// Replace user, password, and dbname with your actual database credentials.
	var err error
	dsn := "user:password@tcp(127.0.0.1:3306)/dbname"
	db, err = sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Failed to open database: %v", err)
	}
	defer db.Close()

	// Ping the database to verify the connection is alive.
	err = db.Ping()
	if err != nil {
		log.Fatalf("Failed to connect to database: %v", err)
	}

	fmt.Println("Successfully connected to the database.")

	// --- Calling the translated function ---
	// Example 1: Add a new student
	err = AddStudent("Alice", 22)
	if err != nil {
		log.Printf("Error adding student: %v\n", err)
	}

	// Example 2: Add another student
	err = AddStudent("Bob", 25)
	if err != nil {
		log.Printf("Error adding student: %v\n", err)
	}
}