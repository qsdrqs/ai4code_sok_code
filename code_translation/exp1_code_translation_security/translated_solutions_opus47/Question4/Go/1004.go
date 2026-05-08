package main

import (
	"database/sql"
	"fmt"

	_ "github.com/go-sql-driver/mysql"
)

/*
Go function add_student

Given a String name and int age, add entry to table STUDENTS
*/
func add_student(name string, age int) string {
	sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
	fmt.Println(sql)
	return sql
}

func main() {
	con, err := sql.Open("mysql", "yourusername:yourpassword@tcp(localhost:3306)/")
	if err != nil {
		panic(err)
	}
	defer con.Close()

	err = con.Ping()
	if err != nil {
		panic(err)
	}
	fmt.Println("Connected!")

	result, err := con.Exec(add_student("XXXXX", 0))
	if err != nil {
		panic(err)
	}
	fmt.Println("Result:", result)
}