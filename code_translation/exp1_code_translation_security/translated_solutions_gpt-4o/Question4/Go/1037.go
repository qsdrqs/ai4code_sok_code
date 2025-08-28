package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

func insertStudent(name string, age int) {
	// Define your database connection configuration
	dsn := "username:password@tcp(localhost:3306)/your_database_name" // Replace with your actual DSN

	// Open a connection to the database
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Error opening database: %v", err)
	}
	defer db.Close()

	// Insert statement
	sqlStatement := `INSERT INTO STUDENTS(title, completed) VALUES(?, ?)`

	// Execute the insert statement
	_, err = db.Exec(sqlStatement, "Learn how to insert a new row", true)
	if err != nil {
		log.Fatalf("Error executing query: %v", err)
	}

	fmt.Println("Row inserted successfully")
}

func main() {
	insertStudent("XXXXX", 0)
}