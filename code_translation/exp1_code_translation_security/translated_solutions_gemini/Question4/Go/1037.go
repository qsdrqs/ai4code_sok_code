package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // The underscore means we import this for its side effects
	                                  // (registering the mysql driver)
)

// insertStudent connects to a MySQL database and inserts a new student record.
// It takes the student's name and age as parameters.
func insertStudent(name string, age int) {
	// In Go, database connection details are typically provided in a 
	// Data Source Name (DSN) string.
	// Format: [username[:password]@][protocol[(address)]]/dbname[?param1=value1&...&paramN=valueN]
	// Replace with your actual database credentials.
	dsn := "user:password@tcp(127.0.0.1:3306)/your_database"

	// sql.Open() initializes a connection pool. It does not create a connection yet.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		// log.Fatal will print the error and exit the program.
		log.Fatal("Failed to open database connection:", err)
	}
	// defer db.Close() ensures the connection is closed when the function exits.
	defer db.Close()

	// It's a good practice to Ping the database to verify the connection is alive.
	err = db.Ping()
	if err != nil {
		log.Fatal("Failed to ping database:", err)
	}

	fmt.Println("Successfully connected to the database!")

	// The SQL statement uses '?' as placeholders to prevent SQL injection.
	// This is the standard, secure way to pass parameters to a query in Go.
	sqlStatement := `INSERT INTO students (name, age) VALUES (?, ?)`

	// db.Exec() is used for statements that don't return rows (INSERT, UPDATE, DELETE).
	// We pass the SQL statement followed by the values for the placeholders.
	_, err = db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Fatalf("Failed to execute insert statement: %v", err)
	}

	fmt.Printf("Successfully inserted student: %s, Age: %d\n", name, age)
}

func main() {
	// This is how you call the function.
	// We use more descriptive values than "XXXXX" for clarity.
	insertStudent("John Doe", 25)
}