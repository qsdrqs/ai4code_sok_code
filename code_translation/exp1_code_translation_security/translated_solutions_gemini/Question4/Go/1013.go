package main

import (
	"bufio"
	"database/sql"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"

	// The underscore `_` is used to import the driver for its side effects
	// (registering itself with the `database/sql` package) without needing
	// to directly reference any of its exported names.
	_ "github.com/go-sql-driver/mysql"
)

// Insert connects to a MySQL database and inserts a new student record based on user input.
func Insert() {
	// 1. Define the database connection string (Data Source Name or DSN).
	// Format: "user:password@tcp(host:port)/dbname"
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/mydb"

	// 2. Open a connection to the database.
	// sql.Open doesn't establish any connections to the database, nor does it
	// validate the driver connection parameters. It simply prepares the DB handle.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		// log.Fatal is equivalent to Print() followed by a call to os.Exit(1).
		log.Fatalf("Failed to prepare database connection: %v", err)
	}
	// Defer the closing of the connection until the function returns.
	defer db.Close()

	// 3. Verify the connection to the database is alive.
	// db.Ping() is the standard way to check if the connection is still valid.
	err = db.Ping()
	if err != nil {
		log.Fatalf("Failed to connect to database: %v", err)
	}
	fmt.Println("Connected!")

	// 4. Get user input for the new record.
	name := readLine("enter a name: ")
	age := readInt("enter an age: ")

	// 5. Prepare the SQL statement for insertion.
	// Using `?` as a placeholder prevents SQL injection attacks.
	sqlStatement := "INSERT INTO students (name, age) VALUES (?, ?)"

	// 6. Execute the SQL statement.
	// Exec is used for statements that don't return rows (INSERT, UPDATE, DELETE).
	result, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Fatalf("Failed to execute insert statement: %v", err)
	}

	// 7. Get the number of rows affected by the query.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		log.Fatalf("Failed to get affected rows: %v", err)
	}

	fmt.Printf("Number of records inserted: %d\n", rowsAffected)
}

// --- Helper Functions (Dependencies for user input) ---

// readLine reads a single line of text from the console.
func readLine(prompt string) string {
	fmt.Print(prompt)
	reader := bufio.NewReader(os.Stdin)
	text, err := reader.ReadString('\n')
	if err != nil {
		log.Fatalf("Error reading input: %v", err)
	}
	// Trim whitespace and newline characters from the input.
	return strings.TrimSpace(text)
}

// readInt reads a line from the console and converts it to an integer.
func readInt(prompt string) int {
	line := readLine(prompt)
	num, err := strconv.Atoi(line)
	if err != nil {
		log.Fatalf("Invalid input: please enter an integer. Error: %v", err)
	}
	return num
}

// main is the entry point of the application.
func main() {
	Insert()
}