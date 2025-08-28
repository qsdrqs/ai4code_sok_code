package main

import (
	"database/sql"
	"fmt"
	"strings"

	_ "github.com/go-sql-driver/mysql" // Import the MySQL driver
)

func main() {
	// Step 1: Define the Data Source Name (DSN)
	dsn := "yourusername:yourpassword@tcp(127.0.0.1:3306)/mydb"

	// Step 2: Open a connection to the MySQL database
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		panic(err)
	}
	defer db.Close() // Ensure the connection is closed when the function exits

	// Step 3: Ping the database to verify the connection
	if err := db.Ping(); err != nil {
		panic(err)
	}
	fmt.Println("Connected!")

	// Step 4: Define the original SQL query with a placeholder
	sqlStr := "INSERT INTO customers (name, address) VALUES ?"

	// Step 5: Define the values to insert (2D slice of interface{})
	values := [][]interface{}{
		{"XXXXX", "XXXXX"},
	}

	// Step 6: Generate placeholders for each row
	rowPlaceholders := make([]string, len(values))
	for i := range values {
		rowPlaceholders[i] = "(?, ?)"
	}
	placeholdersStr := strings.Join(rowPlaceholders, ", ")

	// Step 7: Replace the '?' in the SQL string with the generated placeholders
	finalSQL := strings.Replace(sqlStr, "?", placeholdersStr, 1)

	// Step 8: Flatten the 2D slice into a flat slice of interface{} for arguments
	args := make([]interface{}, 0, len(values)*2)
	for _, row := range values {
		args = append(args, row[0], row[1])
	}

	// Step 9: Execute the query with the flattened arguments
	result, err := db.Exec(finalSQL, args...)
	if err != nil {
		panic(err)
	}

	// Step 10: Retrieve and print the number of affected rows
	affectedRows, err := result.RowsAffected()
	if err != nil {
		panic(err)
	}
	fmt.Printf("Number of records inserted: %d\n", affectedRows)
}