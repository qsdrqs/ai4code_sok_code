package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import is used for the side-effect of registering
	// the mysql driver with the database/sql package.
	_ "github.com/go-sql-driver/mysql"
)

func main() {
	// Note: The original JavaScript code was not tested.
	// This Go code follows modern Go best practices for database access.

	// The Go MySQL driver uses a Data Source Name (DSN) string to connect.
	// Format: [username[:password]@][protocol[(address)]]/dbname[?param1=value1&...&paramN=valueN]
	dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s", "yourusername", "yourpassword", "127.0.0.1:3306", "mydb")

	// Open a connection to the database. sql.Open doesn't actually
	// establish a connection, but prepares a DB object for later use.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		// log.Fatal is equivalent to fmt.Println followed by os.Exit(1).
		log.Fatal("Failed to open database connection: ", err)
	}
	// Defer the closing of the connection until the main function exits.
	defer db.Close()

	// To verify the connection, use Ping().
	err = db.Ping()
	if err != nil {
		log.Fatal("Failed to connect to database: ", err)
	}
	fmt.Println("Connected!")

	// The Go database/sql package does not support the single `?` placeholder
	// for a 2D slice like the Node.js `mysql` driver.
	// You must specify placeholders for each column.
	sqlStatement := "INSERT INTO customers (name, address) VALUES (?, ?)"

	// The values to be inserted.
	// For a bulk insert of many rows, it's more efficient to use a prepared statement in a transaction.
	// For this example, we will insert a single row as shown in the JS.
	values := []string{"XXXXX", "XXXXX"}

	// Execute the SQL statement.
	// Exec is used for statements that don't return rows (INSERT, UPDATE, DELETE).
	result, err := db.Exec(sqlStatement, values[0], values[1])
	if err != nil {
		log.Fatal("Failed to execute insert statement: ", err)
	}

	// Get the number of rows affected by the query.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		log.Fatal("Failed to get affected rows: ", err)
	}

	fmt.Printf("Number of records inserted: %d\n", rowsAffected)
}