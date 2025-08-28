package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func add(name string, age int) {
	// Assuming config is defined in a separate function or file
	config := "user:password@tcp(127.0.0.1:3306)/dbname"

	// Open a connection to the database
	db, err := sql.Open("mysql", config)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Prepare the statement
	stmt := "INSERT INTO todos(title, completed) VALUES (?, ?)"
	res, err := db.Exec(stmt, name, age)
	if err != nil {
		log.Fatal(err)
	}

	// Get the number of affected rows
	rowsAffected, err := res.RowsAffected()
	if err != nil {
		log.Fatal(err)
	}

	fmt.Printf("Row inserted: %d\n", rowsAffected)
}

func main() {
	add("Sample Name", 25)
}