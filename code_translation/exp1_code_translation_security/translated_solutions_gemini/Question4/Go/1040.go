package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import is used to register the sqlite3 driver with the database/sql package.
	_ "github.com/mattn/go-sqlite3"
)

// Student struct to hold the data from our table.
// This makes it easier to work with the query results.
type Student struct {
	Name string
	Age  int
}

// addNewEntry adds a new student to the database.
// It takes the database connection as an argument, which is more idiomatic in Go
// than relying on a global variable.
// It returns an error if any database operation fails.
func addNewEntry(db *sql.DB, name string, age int) error {
	// The CREATE TABLE statement is executed first.
	// The "IF NOT EXISTS" clause prevents an error on subsequent calls.
	// In Go, we use `Exec` for statements that don't return rows.
	createTableSQL := `CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT);`
	_, err := db.Exec(createTableSQL)
	if err != nil {
		return fmt.Errorf("failed to create table: %w", err)
	}

	// Prepare the INSERT statement for execution. This is a good practice
	// to prevent SQL injection attacks.
	stmt, err := db.Prepare("INSERT INTO STUDENTS VALUES (?, ?)")
	if err != nil {
		return fmt.Errorf("failed to prepare statement: %w", err)
	}
	// `defer` ensures the prepared statement is closed when the function exits.
	defer stmt.Close()

	// Execute the prepared statement with the provided name and age.
	_, err = stmt.Exec(name, age)
	if err != nil {
		return fmt.Errorf("failed to execute statement: %w", err)
	}

	return nil
}

func main() {
	// In Go, we open a connection to the database. ":memory:" creates an
	// in-memory database, just like in the JavaScript example.
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		// log.Fatal prints the error and exits the program.
		log.Fatal(err)
	}
	// `defer` ensures the database connection is closed when main() exits.
	defer db.Close()

	// Add the new entries, checking for errors after each call.
	err = addNewEntry(db, "XXXXX", 0)
	if err != nil {
		log.Fatal(err)
	}

	err = addNewEntry(db, "XXXXX", 0)
	if err != nil {
		log.Fatal(err)
	}

	// Query the database for all students. `db.Query` returns a `*sql.Rows` object.
	rows, err := db.Query("SELECT NAME, AGE FROM STUDENTS")
	if err != nil {
		log.Fatal(err)
	}
	// It's crucial to close the rows when we're done with them.
	defer rows.Close()

	// The standard way to iterate over rows in Go is with a for loop.
	fmt.Println("Students in the database:")
	for rows.Next() {
		var student Student
		// Scan copies the columns from the current row into the variables provided.
		if err := rows.Scan(&student.Name, &student.Age); err != nil {
			log.Fatal(err)
		}
		// In Go, printing a struct provides a nicely formatted output.
		fmt.Printf("{Name: %s, Age: %d}\n", student.Name, student.Age)
	}

	// After the loop, we must check for any errors that occurred during iteration.
	if err := rows.Err(); err != nil {
		log.Fatal(err)
	}
}