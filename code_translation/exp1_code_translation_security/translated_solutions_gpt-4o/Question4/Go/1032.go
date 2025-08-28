package main

import (
	"fmt"
)

func addStudent(name string, age int) string {
	sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
	return sql
}

func main() {
	// Example usage:
	// sql := addStudent("XXXXX", 0)
	// fmt.Println(sql)
}