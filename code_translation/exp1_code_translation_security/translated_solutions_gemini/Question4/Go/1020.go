package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import is used for the side-effect of registering
	// the mysql driver with the database/sql package.
	_ "github.com/go-sql-driver/mysql"
)

// Inserter connects to a MySQL database and inserts a new student record.
// It returns an error if any step of the process fails.
// Note: In Go, it's idiomatic for functions that can fail to return an error.
func Inserter(name string, age int) error {
	// 1. Define the connection string (Data Source Name or DSN)
	// Format: user:password@tcp(host:port)/dbname
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/mydb"

	// 2. Open a connection to the database.
	// sql.Open doesn't actually create a connection, but prepares a DB object.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		// If opening the DB object fails, return the error.
		return fmt.Errorf("failed to prepare database connection: %w", err)
	}
	// defer db.Close() ensures the connection is closed when the function exits.
	defer db.Close()

	// 3. Ping the database to verify the connection is alive.
	// This is the first time a real connection is made.
	err = db.Ping()
	if err != nil {
		return fmt.Errorf("failed to connect to database: %w", err)
	}
	log.Println("Connected!")

	// 4. Prepare the SQL statement with placeholders (?) to prevent SQL injection.
	// This is a major security improvement over the original JavaScript code.
	sqlStatement := `INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)`

	// 5. Execute the SQL statement.
	// db.Exec is used for statements that don't return rows (INSERT, UPDATE, DELETE).
	result, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		return fmt.Errorf("failed to execute insert statement: %w", err)
	}

	// 6. (Optional but good practice) Check how many rows were affected.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}
	log.Printf("%d record inserted", rowsAffected)

	// If everything was successful, return nil for the error.
	return nil
}

// main function to demonstrate how to use the Inserter function.
func main() {
	// Example usage:
	err := Inserter("Alice", 30)
	if err != nil {
		// log.Fatalf will print the error and exit the program.
		log.Fatalf("Error inserting record: %v", err)
	}

	log.Println("Successfully inserted record via Inserter function.")
}