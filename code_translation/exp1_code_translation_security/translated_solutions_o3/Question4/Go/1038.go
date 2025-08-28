package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

// insertion adds one row to the STUDENTS table.
func insertion(name string, age int) error {
	// Adjust the Data Source Name to match your own setup.
	dsn := "user:password@tcp(127.0.0.1:3306)/dbname"

	// Open a connection handle; actual connection happens on first use.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		return fmt.Errorf("open database: %w", err)
	}
	defer db.Close()

	// Verify that the database is reachable.
	if err := db.Ping(); err != nil {
		return fmt.Errorf("ping database: %w", err)
	}

	// Execute the INSERT statement.
	const query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
	if _, err := db.Exec(query, name, age); err != nil {
		return fmt.Errorf("insert row: %w", err)
	}

	return nil
}

func main() {
	if err := insertion("Alice", 22); err != nil {
		log.Fatalf("insertion failed: %v", err)
	}
}