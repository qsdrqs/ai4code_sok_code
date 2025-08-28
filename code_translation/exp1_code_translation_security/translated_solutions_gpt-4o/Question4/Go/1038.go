package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func insertion(name string, age int) {
	// Replace with your own connection string
	dsn := "username:password@tcp(127.0.0.1:3306)/dbname"

	// Open a connection to the database
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Check if the connection is successful
	err = db.Ping()
	if err != nil {
		log.Fatal(err)
	}

	// Prepare the SQL statement
	sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

	// Execute the SQL statement
	_, err = db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Record inserted successfully")
}

func main() {
	insertion("John Doe", 25)
}