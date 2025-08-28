// Package main is only here so the snippet compiles; feel free to move
// the function into whatever package you like.
package main

import (
	"database/sql"
	"fmt"
	"log"
)

// db is assumed to be opened/initialised elsewhere in your code (using
// database/sql and the driver of your choice, e.g. _ "github.com/go-sql-driver/mysql").
var db *sql.DB

// add inserts a new row into the STUDENTS table.
func add(name string, age int) {
	// Use placeholders rather than string-concatenation to avoid SQL-injection
	// problems.  The placeholder symbol varies by driver:
	//   MySQL  → "?"
	//   Postgres → "$1", "$2", …
	// Adjust as needed for the driver you actually use.
	const query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

	// Show the query (mimicking console.log in the JS version).
	fmt.Printf("Executing: %s  -- params: %q, %d\n", query, name, age)

	if _, err := db.Exec(query, name, age); err != nil {
		log.Fatal(err) // like `throw err` in JS
	}

	fmt.Println("1 record inserted")
}