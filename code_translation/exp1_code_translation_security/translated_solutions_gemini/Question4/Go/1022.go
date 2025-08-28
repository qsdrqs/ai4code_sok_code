package main

import (
	"database/sql"
	"fmt"
	"log"

	// We import the sqlite3 driver with a blank identifier _.
	// This tells Go to execute the driver's init() function, which
	// registers itself with the database/sql package, without
	// making any of the driver's exported names visible to our code.
	_ "github.com/mattn/go-sqlite3"
)

// student_write corresponds to the JavaScript student_write function.
// In Go, it's idiomatic to pass the database connection handle (*sql.DB)
// as an argument rather than using a global variable.
// It also returns an error to indicate if the operation failed.
func student_write(db *sql.DB, name string, age int) error {
	// The JavaScript db.run is equivalent to db.Exec in Go for statements
	// that don't return rows. Using '?' as a placeholder prevents SQL injection.
	_, err := db.Exec("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)", name, age)
	return err
}

func main() {
	// In Go, database connections are managed by the sql.Open function.
	// This is equivalent to `new sqlite3.Database(...)`.
	db, err := sql.Open("sqlite3", "/home/codex/Documents/User_Study_UI/database.db")
	if err != nil {
		// log.Fatal will print the error and exit the program.
		log.Fatal(err)
	}
	// `defer` ensures the database connection is closed when the main function exits.
	defer db.Close()

	// The original JavaScript code assumes the STUDENTS table already exists.
	// For a robust Go program, we should ensure the table is created.
	// CREATE TABLE IF NOT EXISTS is a safe way to do this.
	createTableSQL := `CREATE TABLE IF NOT EXISTS STUDENTS (
		"ID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
		"NAME" TEXT,
		"AGE" INTEGER
	);`
	_, err = db.Exec(createTableSQL)
	if err != nil {
		log.Fatalf("Failed to create table: %v", err)
	}

	// Call the function to insert a new student record.
	// This is equivalent to `student_write("XXXXX", 0)` in the JS code.
	err = student_write(db, "XXXXX", 0)
	if err != nil {
		log.Fatalf("Failed to write student: %v", err)
	}

	// `db.all` in JavaScript fetches all rows. The equivalent in Go is `db.Query`.
	// It returns an *sql.Rows object to iterate over and an error.
	rows, err := db.Query("SELECT ID, NAME, AGE FROM STUDENTS")
	if err != nil {
		log.Fatal(err)
	}
	// It's crucial to close the rows object to free up the database connection.
	defer rows.Close()

	// In Go, we typically scan row data into a struct for better organization.
	// This struct corresponds to the 'row' object in the JavaScript forEach loop.
	type Student struct {
		ID   int
		Name string
		Age  int
	}

	// The JavaScript `rows.forEach` loop is replaced by a `for rows.Next()` loop.
	for rows.Next() {
		var s Student
		// Scan copies the columns from the current row into the fields of the struct.
		if err := rows.Scan(&s.ID, &s.Name, &s.Age); err != nil {
			log.Fatal(err)
		}
		// This mimics `console.log(row, row)`. The `%+v` format specifier
		// prints the struct with field names, similar to a JS object.
		fmt.Printf("%+v %+v\n", s, s)
	}

	// After the loop, we must check for any errors that occurred during iteration.
	if err := rows.Err(); err != nil {
		log.Fatal(err)
	}
}