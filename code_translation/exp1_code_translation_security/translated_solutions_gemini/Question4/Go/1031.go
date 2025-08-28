package main

import (
	"database/sql"
	"fmt"
	"log"

	// The Go MySQL driver. We import it with a blank identifier "_" because
	// we only need its side effects (registering itself with the database/sql package).
	// We don't need to call any of its functions directly.
	_ "github.com/go-sql-driver/mysql"
)

// insert adds a new student record to the database.
// It returns an error if any step of the process fails.
func insert(name string, age int) error {
	// The Data Source Name (DSN) format for the MySQL driver is:
	// user:password@tcp(host:port)/dbname
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/STUDENTS"

	// sql.Open() prepares a database connection pool. It does not
	// immediately connect to the database.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		// If the DSN is malformed, an error is returned here.
		return fmt.Errorf("failed to open database: %w", err)
	}
	// defer db.Close() ensures the connection pool is closed when the function exits.
	defer db.Close()

	// db.Ping() is used to verify that the connection to the database is alive.
	// This is equivalent to the con.connect() call in the JavaScript example.
	err = db.Ping()
	if err != nil {
		return fmt.Errorf("failed to connect to database: %w", err)
	}

	fmt.Println("Connected!")

	// The SQL statement uses "?" as a placeholder for parameters.
	// This prevents SQL injection attacks.
	sqlStatement := "INSERT INTO students (name, age) VALUES (?, ?)"

	// db.Exec() is used for statements that don't return rows (INSERT, UPDATE, DELETE).
	// The arguments following the query string are the parameters for the placeholders.
	result, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		return fmt.Errorf("failed to execute insert: %w", err)
	}

	// You can optionally get the number of rows affected.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	fmt.Printf("%d record inserted\n", rowsAffected)
	return nil
}

// main function to run the example
func main() {
	// Call the insert function with sample data.
	// In a real application, you would get this data from user input, an API request, etc.
	err := insert("Bob", 25)
	if err != nil {
		// log.Fatal will print the error and exit the program.
		log.Fatal(err)
	}

	fmt.Println("Insert operation completed successfully.")
}