package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

// InsertInto inserts a new student record into the STUDENTS table.
func InsertInto(name string, age int) {
	// Define the data source name (DSN) for the MySQL connection.
	// Replace the placeholders with your actual database credentials and details.
	dsn := "username:password@tcp(localhost:3306)/dbname"

	// Open a connection to the database.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Error opening database: %v", err)
	}
	defer db.Close()

	// Prepare the SQL statement for insertion.
	sql := "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)"

	// Execute the SQL statement.
	_, err = db.Exec(sql, name, age)
	if err != nil {
		log.Fatalf("Error executing query: %v", err)
	}

	fmt.Println("Record inserted successfully")
}

func main() {
	// Example usage of the InsertInto function.
	InsertInto("John Doe", 25)
}