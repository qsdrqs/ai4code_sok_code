package main

import (
	"context"
	"database/sql"
	"fmt"
	"log"
	"time"

	// The blank import `_` is used to register the MySQL driver.
	// The `database/sql` package will use it automatically.
	_ "github.com/go-sql-driver/mysql"
)

// db is a package-level variable to hold the database connection pool.
// It's initialized once in the main function.
var db *sql.DB

// AddStudent inserts a new student record into the database.
// It takes a context for cancellation and timeout control.
func AddStudent(ctx context.Context, name string, age int) error {
	// The `?` are placeholders to prevent SQL injection.
	// It's also good practice to explicitly name the columns you are inserting into.
	query := "INSERT INTO STUDENTS (name, age) VALUES (?, ?)"

	// ExecContext is used for queries that don't return rows (INSERT, UPDATE, DELETE).
	// We pass the name and age as separate arguments. The driver will safely
	// substitute them for the `?` placeholders.
	_, err := db.ExecContext(ctx, query, name, age)
	if err != nil {
		// If there's an error, we return it to the caller.
		return fmt.Errorf("could not add student %s: %w", name, err)
	}

	return nil // Return nil on success
}

func main() {
	// The Data Source Name (DSN) contains the connection details.
	// Format: user:password@tcp(host:port)/dbname
	// Replace with your actual database credentials.
	dsn := "user:password@tcp(127.0.0.1:3306)/your_database"

	// sql.Open() initializes a connection pool. It does not create a connection yet.
	var err error
	db, err = sql.Open("mysql", dsn)
	if err != nil {
		// log.Fatal will print the error and exit the program.
		log.Fatalf("Error opening database: %v", err)
	}
	// defer db.Close() ensures the connection pool is closed when main exits.
	defer db.Close()

	// To verify the connection is working, we can Ping the database.
	err = db.Ping()
	if err != nil {
		log.Fatalf("Error connecting to database: %v", err)
	}

	fmt.Println("Successfully connected to the database!")

	// --- Example Usage of AddStudent ---

	// Create a context with a timeout for the database operation.
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	// Call the function to add a new student.
	studentName := "Alice"
	studentAge := 22
	err = AddStudent(ctx, studentName, studentAge)
	if err != nil {
		log.Printf("Failed to add student: %v", err)
	} else {
		fmt.Printf("Successfully added student: %s\n", studentName)
	}
}