package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import is used to register the sqlite3 driver.
	_ "github.com/mattn/go-sqlite3"
)

// CreateTable creates the "students" table in the database if it doesn't already exist.
// It takes a database connection pool (*sql.DB) as an argument.
// In Go, functions that can fail return an 'error' value.
func CreateTable(db *sql.DB) error {
	// The SQL statement is often defined in a multi-line raw string literal.
	// "IF NOT EXISTS" prevents an error if the table has already been created.
	sqlStmt := `
	CREATE TABLE IF NOT EXISTS students (
		id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
		name TEXT,
		age INTEGER
	);
	`
	// db.Exec executes a query without returning any rows.
	// It's ideal for CREATE, INSERT, UPDATE, or DELETE statements.
	_, err := db.Exec(sqlStmt)
	if err != nil {
		// Instead of an error callback, we check the returned error.
		log.Printf("Error creating table: %v", err)
		return err
	}

	// Instead of a success callback, we can log a message after a successful operation.
	fmt.Println("Table 'students' created or already exists.")
	return nil
}

// InsertStudent inserts a new student record into the 'students' table.
// It uses a transaction to ensure the operation is atomic.
func InsertStudent(db *sql.DB, name string, age int) error {
	// The '?' are placeholders for parameters to prevent SQL injection.
	sqlStmt := `INSERT INTO students(name, age) VALUES (?, ?)`

	// Begin a transaction. This is equivalent to JavaScript's db.transaction().
	tx, err := db.Begin()
	if err != nil {
		log.Printf("Error beginning transaction: %v", err)
		return err
	}

	// Prepare the statement within the transaction for execution.
	stmt, err := tx.Prepare(sqlStmt)
	if err != nil {
		// If preparing fails, we must roll back the transaction.
		tx.Rollback()
		log.Printf("Error preparing statement: %v", err)
		return err
	}
	// defer ensures the statement is closed when the function exits.
	defer stmt.Close()

	// Execute the prepared statement with the provided name and age.
	_, err = stmt.Exec(name, age)
	if err != nil {
		// If execution fails, roll back the transaction.
		tx.Rollback()
		log.Printf("Error executing insert: %v", err)
		return err
	}

	// If everything was successful, commit the transaction to save the changes.
	return tx.Commit()
}

// main function to demonstrate the usage.
func main() {
	// In Go, we explicitly open a connection to a database file.
	// This is analogous to JavaScript's openDatabase().
	// The file "students.db" will be created in the same directory.
	db, err := sql.Open("sqlite3", "./students.db")
	if err != nil {
		log.Fatal(err)
	}
	// defer ensures the database connection is closed when main() finishes.
	defer db.Close()

	// Call the function to create the table.
	if err := CreateTable(db); err != nil {
		log.Fatal("Failed to setup the database table:", err)
	}

	// Insert a couple of students.
	studentsToInsert := map[string]int{
		"Alice": 21,
		"Bob":   25,
		"Charlie": 23,
	}

	for name, age := range studentsToInsert {
		err := InsertStudent(db, name, age)
		if err != nil {
			log.Printf("Failed to insert student %s: %v", name, err)
		} else {
			fmt.Printf("Successfully inserted student: %s, Age: %d\n", name, age)
		}
	}
}