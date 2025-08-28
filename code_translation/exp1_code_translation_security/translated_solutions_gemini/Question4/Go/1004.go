package main

import (
	"database/sql"
	"fmt"
	"log"

	// The underscore before the import path is a Go convention.
	// It signifies that the package is being imported for its side effects only.
	// In this case, it registers the MySQL driver with the `database/sql` package.
	_ "github.com/go-sql-driver/mysql"
)

/*
Go function addStudent

This function is the idiomatic and secure way to add a student in Go.
Instead of building a raw SQL string (which is vulnerable to SQL injection),
it executes a prepared statement with parameters.

It takes a database connection handle (*sql.DB), a name, and an age.
It returns the number of rows affected and any error that occurred.
*/
func addStudent(db *sql.DB, name string, age int) (int64, error) {
	// The '?' are placeholders for parameters. The database driver will
	// safely substitute them with the values provided, preventing SQL injection.
	query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

	// db.Exec is used for statements that don't return rows (INSERT, UPDATE, DELETE).
	result, err := db.Exec(query, name, age)
	if err != nil {
		return 0, err
	}

	// We can get the number of rows affected by the query.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return 0, err
	}

	fmt.Printf("Executing SQL: %s with values [%s, %d]\n", query, name, age)
	return rowsAffected, nil
}

func main() {
	// The connection string (DSN - Data Source Name) format for the Go driver.
	// Format: "user:password@tcp(host:port)/dbname"
	// Make sure to replace with your actual credentials.
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/"

	// sql.Open() initializes a connection pool. It does not create a connection yet.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		// log.Fatal will print the error and exit the program.
		log.Fatal(err)
	}
	// defer db.Close() ensures that the connection is closed when the main function exits.
	defer db.Close()

	// To verify that a connection can be made, we use db.Ping().
	err = db.Ping()
	if err != nil {
		log.Fatal("Failed to connect to the database:", err)
	}
	fmt.Println("Connected!")

	// Call our function to add a student.
	rowsAffected, err := addStudent(db, "Maria", 31)
	if err != nil {
		log.Fatal("Failed to add student:", err)
	}

	// In Go, the result of an INSERT is typically the number of rows affected
	// or the ID of the last inserted row, not a complex object.
	fmt.Printf("Result: %d row(s) inserted.\n", rowsAffected)
}