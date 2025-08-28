package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

func main() {
	// Database connection details
	dsn := "yourusername:yourpassword@tcp(127.0.0.1:3306)/mydb"

	// Open a connection to the database
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Error opening database connection: %v", err)
	}
	defer db.Close()

	// Test the connection
	err = db.Ping()
	if err != nil {
		log.Fatalf("Error connecting to the database: %v", err)
	}
	fmt.Println("Connected!")

	// SQL query to insert data
	sqlQuery := "INSERT INTO customers (name, address) VALUES (?, ?)"
	values := [][]interface{}{
		{"XXXXX", "XXXXX"},
	}

	// Execute the query for each set of values
	for _, value := range values {
		result, err := db.Exec(sqlQuery, value...)
		if err != nil {
			log.Fatalf("Error inserting data: %v", err)
		}

		// Get the number of rows affected
		rowsAffected, err := result.RowsAffected()
		if err != nil {
			log.Fatalf("Error getting rows affected: %v", err)
		}
		fmt.Printf("Number of records inserted: %d\n", rowsAffected)
	}
}