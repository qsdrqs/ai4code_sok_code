package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

func inserter(name string, age int) {
	// Define the connection string
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/mydb"

	// Open a connection to the database
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Error opening database: %v", err)
	}
	defer db.Close()

	// Test the connection
	err = db.Ping()
	if err != nil {
		log.Fatalf("Error connecting to the database: %v", err)
	}
	fmt.Println("Connected!")

	// Prepare the SQL statement
	sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

	// Execute the SQL statement
	_, err = db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Fatalf("Error inserting record: %v", err)
	}
	fmt.Println("1 record inserted")
}

func main() {
	// Example usage
	inserter("John Doe", 25)
}