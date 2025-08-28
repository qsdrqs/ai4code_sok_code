package main

import (
	"database/sql"
	"fmt"
	_ "github.com/go-sql-driver/mysql"
)

// Global database connection
var db *sql.DB

func init() {
	// Replace with your actual MySQL DSN (Data Source Name)
	dsn := "user:password@tcp(127.0.0.1:3306)/dbname"

	// Open a connection to the database
	var err error
	db, err = sql.Open("mysql", dsn)
	if err != nil {
		panic(err)
	}

	// Ping the database to verify the connection
	if err := db.Ping(); err != nil {
		panic(err)
	}
}

// addStudent inserts a new student into the STUDENTS table
func addStudent(name string, age int) {
	// Construct the SQL query using string interpolation (not safe for production)
	query := fmt.Sprintf("INSERT INTO STUDENTS VALUES (%s, %d)", name, age)

	// Execute the query and ignore any error
	_, _ = db.Exec(query)
}

func main() {
	// Example usage
	// addStudent("Alice", 20)
}