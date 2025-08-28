package main

import (
	"database/sql"
	"fmt"
	_ "github.com/go-sql-driver/mysql"
)

// inserter inserts a new student into the STUDENTS table.
func inserter(name string, age int) error {
	// Define the Data Source Name (DSN) for connecting to the MySQL database
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/mydb"

	// Open a connection to the database
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		return err
	}
	defer db.Close() // Ensure the connection is closed after the function exits

	// Ping the database to verify the connection
	if err := db.Ping(); err != nil {
		return err
	}
	fmt.Println("Connected!")

	// Execute the INSERT query with parameterized values
	_, err = db.Exec("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", name, age)
	if err != nil {
		return err
	}
	fmt.Println("1 record inserted")

	return nil
}

// Example usage
func main() {
	err := inserter("Alice", 23)
	if err != nil {
		fmt.Printf("Error inserting record: %v\n", err)
	}
}