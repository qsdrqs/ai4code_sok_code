// NOTE: This code is **not** tested – it is a straightforward translation of the
// supplied JavaScript snippet into Go.  All imports (including the MySQL driver)
// are referenced here so that a normal “go mod init / go mod tidy” will fetch
// them automatically.

package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

func main() {
	// ---------------------------------------------------------------------
	// 1. Open a connection to MySQL
	// ---------------------------------------------------------------------
	dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s?parseTime=true",
		"yourusername",   // user
		"yourpassword",   // password
		"127.0.0.1:3306", // host[:port]
		"mydb",           // database
	)

	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("unable to open DB connection: %v", err)
	}
	defer db.Close()

	// Ping to verify that the connection works.
	if err := db.Ping(); err != nil {
		log.Fatalf("unable to connect to DB: %v", err)
	}
	log.Println("Connected!")

	// ---------------------------------------------------------------------
	// 2. Build & execute an INSERT similar to the JS code
	// ---------------------------------------------------------------------
	values := [][]string{
		{"XXXXX", "XXXXX"},
		// Add more rows here if needed, e.g. {"A", "B"}, {"C", "D"}, ...
	}

	// We need to construct “…VALUES (?, ?), (?, ?), …” dynamically in Go.
	query := "INSERT INTO customers (name, address) VALUES "
	args := make([]interface{}, 0, len(values)*2)

	for i, v := range values {
		query += "(?, ?)"
		if i < len(values)-1 {
			query += ", "
		}
		args = append(args, v[0], v[1]) // name, address
	}

	result, err := db.Exec(query, args...)
	if err != nil {
		log.Fatalf("insert failed: %v", err)
	}

	affected, err := result.RowsAffected()
	if err != nil {
		log.Fatalf("cannot read affected rows: %v", err)
	}
	log.Printf("Number of records inserted: %d\n", affected)
}