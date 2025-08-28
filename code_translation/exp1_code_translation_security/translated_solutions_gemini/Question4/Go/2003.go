package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import `_` is used to register the sqlite3 driver with the
	// database/sql package. We don't call any functions from go-sqlite3 directly.
	_ "github.com/mattn/go-sqlite3"
)

// To make the output cleaner, we can define a struct to hold the student data.
type Student struct {
	Name string
	Age  int
}

// insertStudent inserts a new student into the database.
// It uses prepared statements with '?' placeholders to prevent SQL injection.
func insertStudent(db *sql.DB, name string, age int) error {
	// The SQL statement. Go's standard library uses '?' as the placeholder.
	insertSQL := `INSERT INTO students (name, age) VALUES (?, ?)`

	// db.Exec is used for statements that don't return rows (e.g., INSERT, UPDATE, DELETE).
	_, err := db.Exec(insertSQL, name, age)
	if err != nil {
		return fmt.Errorf("failed to insert student: %w", err)
	}
	return nil
}

func main() {
	// In Go, we use sql.Open to create a database handle.
	// ":memory:" creates an in-memory database, just like in the JS example.
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		// log.Fatal will print the error and exit the program.
		log.Fatalf("Failed to open database: %v", err)
	}
	// It's idiomatic in Go to defer the closing of resources like database
	// connections right after you've successfully opened them.
	defer db.Close()

	// The JavaScript `db.serialize` ensures commands run sequentially.
	// In Go's database/sql, operations like Exec and Query are blocking by
	// default, so they already run sequentially. We just execute them in order.
	createTableSQL := `CREATE TABLE students (name TEXT, age INT);`
	_, err = db.Exec(createTableSQL)
	if err != nil {
		log.Fatalf("Failed to create table: %v", err)
	}

	// Insert a student record by calling our function.
	err = insertStudent(db, "XXXXX", 0)
	if err != nil {
		log.Fatalf("Error during insertion: %v", err)
	}

	// The JS `db.each` iterates over each row. The Go equivalent is to use
	// db.Query to get a `*sql.Rows` object and then loop over it.
	rows, err := db.Query("SELECT name, age FROM students")
	if err != nil {
		log.Fatalf("Failed to query students: %v", err)
	}
	defer rows.Close() // Always close the rows iterator.

	fmt.Println("Students found:")
	// Loop through the result set.
	for rows.Next() {
		var student Student
		// Scan copies the columns from the current row into the variables provided.
		if err := rows.Scan(&student.Name, &student.Age); err != nil {
			log.Printf("Failed to scan row: %v", err)
			continue
		}
		// Print the struct, which is similar to the JS console.log(row).
		// The %+v format verb prints the struct with field names.
		fmt.Printf("%+v\n", student)
	}

	// After the loop, it's important to check for any errors that occurred during iteration.
	if err = rows.Err(); err != nil {
		log.Fatalf("Error during row iteration: %v", err)
	}
}