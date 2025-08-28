package main

import (
	"database/sql"
	"log"

	// The blank identifier _ is used because we only need the side effects
	// of the driver's init() function, which registers itself with the
	// database/sql package. We don't need to call any functions from the
	// driver package directly.
	_ "github.com/mattn/go-sqlite3"
)

// To run this code:
// 1. Initialize a Go module: go mod init example.com/sql-translate
// 2. Get the dependency: go get github.com/mattn/go-sqlite3
// 3. Run the file: go run .

func main() {
	// In Go, we open a connection to the database using sql.Open.
	// It returns a connection pool object (*sql.DB) and an error.
	// The file "studentdb" will be created if it doesn't exist.
	db, err := sql.Open("sqlite3", "./studentdb")
	if err != nil {
		log.Fatal(err)
	}
	// The defer statement ensures that the database connection is closed
	// when the main function exits.
	defer db.Close()

	// We use db.Exec for statements that don't return rows, like CREATE TABLE.
	createTableSQL := `CREATE TABLE IF NOT EXISTS STUDENTS (
		"NAME" TEXT,
		"AGE" INT
	);`

	_, err = db.Exec(createTableSQL)
	if err != nil {
		log.Fatalf("Failed to create table: %v", err)
	}

	log.Println("Table created successfully (if it didn't exist).")

	// Call the insert function for each student.
	insert(db, "Foo", 42)
	insert(db, "Bar", 55)

	log.Println("Finished inserting records.")

	// The commented-out section from the JS code is translated below.
	// Uncomment the line below to see the contents of the database.
	// readAndPrintStudents(db)
}

// insert is the Go equivalent of the JavaScript insert function.
// It takes the database connection pool, name, and age as arguments.
// Note: The `db.serialize` from the JS code is not needed here. The `database/sql`
// package in Go handles concurrency safely. For this simple sequential program,
// the calls will execute in order.
func insert(db *sql.DB, name string, age int) {
	log.Printf("Inserting student: %s, Age: %d\n", name, age)

	// Preparing statements is a best practice for security (prevents SQL injection)
	// and performance if you're running the same query multiple times.
	stmt, err := db.Prepare("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)")
	if err != nil {
		log.Printf("Error preparing statement: %v\n", err)
		return
	}
	// We defer closing the statement to prevent resource leaks.
	defer stmt.Close()

	// Execute the prepared statement with the provided arguments.
	_, err = stmt.Exec(name, age)
	if err != nil {
		log.Printf("Error executing insert: %v\n", err)
	}
}

/*
// This function is the Go equivalent of the commented-out `db.each` in the JS code.
// It queries the database and prints each row.
func readAndPrintStudents(db *sql.DB) {
	log.Println("Reading all students from the database:")

	// db.Query is used for SELECT statements that return rows.
	rows, err := db.Query("SELECT NAME, AGE FROM STUDENTS")
	if err != nil {
		log.Printf("Error querying students: %v\n", err)
		return
	}
	defer rows.Close()

	// We iterate over the returned rows.
	for rows.Next() {
		var name string
		var age int
		// For each row, we scan the column values into Go variables.
		if err := rows.Scan(&name, &age); err != nil {
			log.Printf("Error scanning row: %v\n", err)
			continue
		}
		log.Printf("  - Found row: Name=%s, Age=%d\n", name, age)
	}

	// It's important to check for errors that may have occurred during iteration.
	if err := rows.Err(); err != nil {
		log.Printf("Error during rows iteration: %v\n", err)
	}
}
*/