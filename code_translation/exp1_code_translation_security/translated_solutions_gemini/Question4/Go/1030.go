package main

import (
	"database/sql"
	"fmt"
	"log"
	"os"

	// The blank import is used to register the sqlite3 driver.
	_ "github.com/mattn/go-sqlite3"
)

// main is the entry point of the Go program.
// It corresponds to the IIFE (Immediately Invoked Function Expression) in the JavaScript code.
func main() {
	// The active path in the JS code is a chain of operations starting
	// with creating the database. We replicate that flow here.
	createDatabase()

	// The commented-out section in the original JavaScript demonstrated async issues
	// with setTimeout. In Go, operations are synchronous by default, so we don't
	// need to simulate delays to ensure order. If concurrency were needed,
	// Go would use goroutines and channels.
}

// createDatabase handles the creation of the database file and the connection.
// It then proceeds to create the tables.
func createDatabase() {
	fmt.Println("created database")

	// For a clean run every time, we remove the old database file.
	os.Remove("./temp.db")

	// sql.Open creates a database handle but doesn't open a connection immediately.
	// The connection is established on the first actual database operation.
	db, err := sql.Open("sqlite3", "./temp.db")
	if err != nil {
		// log.Fatalf prints the error and exits the program, similar to the JS `exit(1)`.
		log.Fatalf("Getting error %s", err)
	}
	// defer ensures that the database connection is closed when the function exits.
	defer db.Close()

	// The JS code calls createTables2 from within the connection callback.
	// In Go, we simply call it sequentially after the connection is set up.
	createTables2(db)
}

// createTables2 creates the 'students' table in the database.
// The name is kept as 'createTables2' to match the original JS function.
func createTables2(db *sql.DB) {
	fmt.Println("pre-created tables")

	// SQL statement to create the table.
	sqlStmt := `CREATE TABLE students(name TEXT, age INT);`

	// db.Exec is used for SQL statements that don't return rows (e.g., CREATE, INSERT, UPDATE).
	_, err := db.Exec(sqlStmt)
	if err != nil {
		// Log the error if the table creation fails.
		log.Printf("Error creating table: %q: %s\n", err, sqlStmt)
		return
	}

	// In the JS code, insert() is called from the callback of the table creation.
	// In Go, since db.Exec is blocking, we can call it directly on the next line.
	insert(db, "John Doe", 30)
}

// insert adds a new row to the 'students' table.
func insert(db *sql.DB, name string, age int) {
	// We use a parameterized query with '?' to prevent SQL injection vulnerabilities.
	sqlStmt := "INSERT INTO students(name, age) VALUES (?, ?)"

	_, err := db.Exec(sqlStmt, name, age)
	if err != nil {
		// Match the JS error logging style.
		log.Printf("Error: %s", err.Error())
		return
	}

	fmt.Println("row added successfully")
}