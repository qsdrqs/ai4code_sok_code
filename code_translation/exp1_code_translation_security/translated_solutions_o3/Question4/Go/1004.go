// Go equivalent of the JavaScript sample ― minimal, 1-for-1 translation.
//
// All external dependencies (database/sql and github.com/go-sql-driver/mysql)
// are assumed to be available when this code is built/run.

package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

/*
addStudent replicates the JS add_student function.

Given a name (string) and age (int), it returns the literal SQL string that
will be executed.  (Exactly mirrors the JS behaviour; in production you’d
typically use parameterised queries instead.)
*/
func addStudent(name string, age int) string {
	sqlStmt := fmt.Sprintf(
		"INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
		name, age,
	)
	fmt.Println(sqlStmt) // mimic console.log in JS
	return sqlStmt
}

func main() {
	// Same connection parameters that the JS code used.
	// Format: <user>:<password>@tcp(<host>:<port>)/<database>
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/" // no DB selected, same as JS sample
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("opening connection: %v", err)
	}
	defer db.Close()

	// Verify we can reach the server (mirrors con.connect in JS).
	if err = db.Ping(); err != nil {
		log.Fatalf("ping: %v", err)
	}
	fmt.Println("Connected!")

	// Build and execute the INSERT exactly as in JS.
	query := addStudent("XXXXX", 0)

	res, err := db.Exec(query)
	if err != nil {
		log.Fatalf("exec: %v", err)
	}

	affected, _ := res.RowsAffected()
	fmt.Printf("Result: %d row(s) affected\n", affected)
}