package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

// inserter inserts a student record into the STUDENTS table.
func inserter(name string, age int) {
	// Replace with your actual credentials / host / database.
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/mydb"

	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Verify that the connection is valid.
	if err := db.Ping(); err != nil {
		log.Fatal(err)
	}
	fmt.Println("Connected!")

	// Insert the record using a prepared statement.
	const query = `INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)`
	if _, err := db.Exec(query, name, age); err != nil {
		log.Fatal(err)
	}

	fmt.Println("1 record inserted")
}

func main() {
	// Example call
	inserter("Alice", 21)
}