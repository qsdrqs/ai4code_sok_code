package main

import (
	"database/sql"
	"log"
	_ "github.com/go-sql-driver/mysql"
)

func createEntry(db *sql.DB, name string, age int) {
	// Prepare the SQL statement
	stmt, err := db.Prepare("INSERT INTO STUDENTS (name, age) VALUES (?, ?)")
	if err != nil {
		log.Fatal(err)
	}
	defer stmt.Close()

	// Execute the SQL statement
	_, err = stmt.Exec(name, age)
	if err != nil {
		log.Fatal(err)
	}
}

func main() {
	// Example of how to connect to the database
	// Replace with your own connection string
	dsn := "user:password@tcp(127.0.0.1:3306)/dbname"
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Call the createEntry function
	createEntry(db, "John Doe", 25)
}