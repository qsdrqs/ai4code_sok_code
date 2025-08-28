package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import `_` is used to register the driver with the `database/sql` package.
	_ "github.com/mattn/go-sqlite3"
)

// add_new_entry adds a new student to the database and then prints all entries.
func addNewEntry(name string, age int) {
	// 1. Open a connection to the SQLite database file.
	// sql.Open doesn't create the file if it doesn't exist, but the driver will
	// when the first query is executed.
	db, err := sql.Open("sqlite3", "./database.db")
	if err != nil {
		log.Fatalf("Failed to open database: %v", err)
	}
	// `defer db.Close()` ensures the database connection is closed when the function exits.
	defer db.Close()

	// (Optional but good practice) Create the table if it doesn't exist.
	// This makes the function runnable on its own.
	createTableSQL := `CREATE TABLE IF NOT EXISTS STUDENTS (
		"NAME" TEXT,
		"AGE" INTEGER
	);`
	_, err = db.Exec(createTableSQL)
	if err != nil {
		log.Fatalf("Failed to create table: %v", err)
	}

	// 2. Prepare the INSERT statement.
	// Using prepared statements is a security best practice to prevent SQL injection.
	stmt, err := db.Prepare("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)")
	if err != nil {
		log.Fatalf("Failed to prepare statement: %v", err)
	}
	defer stmt.Close()

	// 3. Execute the prepared statement with the provided arguments.
	_, err = stmt.Exec(name, age)
	if err != nil {
		log.Fatalf("Failed to execute statement: %v", err)
	}

	fmt.Println("New entry added. Current database entries:")

	// 4. Query the database to select all students.
	rows, err := db.Query("SELECT rowid AS id, NAME, AGE FROM STUDENTS")
	if err != nil {
		log.Fatalf("Failed to query database: %v", err)
	}
	defer rows.Close()

	// 5. Iterate over the returned rows.
	for rows.Next() {
		var id int
		var rowName string
		var rowAge int

		// Scan the values from the current row into variables.
		if err := rows.Scan(&id, &rowName, &rowAge); err != nil {
			log.Printf("Failed to scan row: %v", err)
			continue
		}
		// Print the row data, similar to console.log in JavaScript.
		fmt.Printf("%d: %s %d\n", id, rowName, rowAge)
	}

	// Check for any errors that occurred during the iteration.
	if err := rows.Err(); err != nil {
		log.Fatalf("Error during row iteration: %v", err)
	}
}

func main() {
	// Call the function with the same sample data.
	addNewEntry("XXXXX", 0)
}