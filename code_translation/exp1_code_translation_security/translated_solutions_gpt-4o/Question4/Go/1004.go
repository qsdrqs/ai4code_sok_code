package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

// addStudent constructs an SQL query to insert a student into the STUDENTS table.
func addStudent(name string, age int) string {
	sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
	fmt.Println(sql)
	return sql
}

func main() {
	// Define the data source name (DSN) for connecting to the MySQL database.
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/yourdatabase"

	// Open a connection to the database.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Verify the connection to the database.
	err = db.Ping()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Connected!")

	// Execute the SQL query to add a student.
	query := addStudent("XXXXX", 0)
	result, err := db.Exec(query)
	if err != nil {
		log.Fatal(err)
	}

	// Print the result of the query execution.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("Result: %d rows affected\n", rowsAffected)
}