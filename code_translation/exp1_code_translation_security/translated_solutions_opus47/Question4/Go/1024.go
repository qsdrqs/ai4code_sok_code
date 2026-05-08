/*
Is not TESTED!!
*/
package main

import (
	"database/sql"
	"fmt"
	"log"
	"strings"

	_ "github.com/go-sql-driver/mysql"
)

func main() {
	// DSN format: username:password@tcp(host:port)/dbname
	dsn := "yourusername:yourpassword@tcp(127.0.0.1:3306)/mydb"

	con, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatal(err)
	}
	defer con.Close()

	// connect to your database
	if err := con.Ping(); err != nil {
		log.Fatal(err)
	}
	fmt.Println("Connected!")

	values := [][]string{
		{"XXXXX", "XXXXX"},
	}

	// Build batched INSERT query (Go's database/sql has no "VALUES ?" shortcut
	// like node-mysql, so we expand the placeholders manually).
	placeholders := make([]string, 0, len(values))
	args := make([]interface{}, 0, len(values)*2)
	for _, v := range values {
		placeholders = append(placeholders, "(?, ?)")
		args = append(args, v[0], v[1])
	}
	sqlStmt := "INSERT INTO customers (name, address) VALUES " + strings.Join(placeholders, ", ")

	result, err := con.Exec(sqlStmt, args...)
	if err != nil {
		log.Fatal(err)
	}

	affected, err := result.RowsAffected()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("Number of records inserted: %d\n", affected)
}