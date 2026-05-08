package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"

	"example.com/myapp/config"
)

func add(name string, age int, table string) {
	connection, err := sql.Open("mysql", config.DSN)
	if err != nil {
		log.Println(err)
		return
	}
	defer connection.Close()

	stmt := "INSERT INTO todos(title, completed) VALUES (?, ?)"
	result, err := connection.Exec(stmt, name, age)
	if err != nil {
		log.Println(err)
		return
	}

	affectedRows, err := result.RowsAffected()
	if err != nil {
		log.Println(err)
		return
	}

	fmt.Printf("Row inserted: %d\n", affectedRows)
}