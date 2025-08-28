package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func insert(name string, age int) {
	// Define the data source name (DSN)
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/STUDENTS"

	// Open a connection to the database
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Test the connection
	err = db.Ping()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Connected!")

	// Prepare the SQL statement
	sqlStatement := "INSERT INTO students (name, age) VALUES (?, ?)"

	// Execute the SQL statement
	_, err = db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("1 record inserted")
}

func main() {
	// Example usage
	insert("John Doe", 25)
}