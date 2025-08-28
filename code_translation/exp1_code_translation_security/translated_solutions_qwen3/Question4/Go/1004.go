package main

import (
	"database/sql"
	"fmt"
	_ "github.com/go-sql-driver/mysql"
)

// addStudent constructs an SQL INSERT statement for the STUDENTS table
func addStudent(name string, age int) string {
	sqlStr := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
	fmt.Println(sqlStr)
	return sqlStr
}

func main() {
	// Generate the SQL statement
	sql := addStudent("XXXXX", 0)

	// Define the Data Source Name (DSN) for connecting to MySQL
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/"

	// Open a connection to the database
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		panic(err)
	}
	defer db.Close()

	// Ping the database to verify the connection
	if err := db.Ping(); err != nil {
		panic(err)
	}
	fmt.Println("Connected!")

	// Execute the SQL query
	result, err := db.Exec(sql)
	if err != nil {
		panic(err)
	}

	// Log the result (optional: you can inspect the result object for more details)
	fmt.Println("Result:", result)
}