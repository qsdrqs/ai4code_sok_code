package main

import (
	"database/sql"
	"fmt"
	_ "github.com/go-sql-driver/mysql"
)

// add inserts a new row into the 'todos' table with the given name and age.
// The 'table' parameter is not used in this function.
func add(name string, age int, _ string) {
	// Hardcoded DSN (Data Source Name) based on the original config.js
	dsn := "user:password@tcp(127.0.0.1:3306)/dbname?charset=utf8mb4&parseTime=True&loc=Local"

	// Open a new connection to the database
	connection, err := sql.Open("mysql", dsn)
	if err != nil {
		fmt.Println("Error opening database connection:", err)
		return
	}
	defer connection.Close()

	// Prepare the SQL statement
	stmt := "INSERT INTO todos (title, completed) VALUES (?, ?)"

	// Execute the query with the provided values
	result, err := connection.Exec(stmt, name, age)
	if err != nil {
		fmt.Println("Error executing query:", err)
		return
	}

	// Get the number of affected rows
	rowsAffected, _ := result.RowsAffected()
	fmt.Printf("Row inserted: %d\n", rowsAffected)
}