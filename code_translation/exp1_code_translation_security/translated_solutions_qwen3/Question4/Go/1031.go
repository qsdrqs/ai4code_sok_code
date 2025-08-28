package main

import (
	"database/sql"
	"fmt"
	_ "github.com/go-sql-driver/mysql" // Import the MySQL driver
)

// insert inserts a new student into the 'students' table
func insert(name string, age int) {
	// Define the Data Source Name (DSN)
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/STUDENTS"

	// Open a connection to the database
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		panic(err)
	}
	defer db.Close() // Ensure the connection is closed after the function exits

	// Ping the database to verify the connection
	err = db.Ping()
	if err != nil {
		panic(err)
	}

	fmt.Println("Connected!")

	// Define the SQL statement with placeholders
	sqlStr := "INSERT INTO students (name, age) VALUES (?, ?)"

	// Execute the insert query with the provided parameters
	result, err := db.Exec(sqlStr, name, age)
	if err != nil {
		panic(err)
	}

	// Optional: Check the number of rows affected
	rowsAffected, _ := result.RowsAffected()
	fmt.Printf("%d record inserted\n", rowsAffected)
}