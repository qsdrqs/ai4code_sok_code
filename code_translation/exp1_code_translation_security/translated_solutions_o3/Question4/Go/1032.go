package main

import "fmt"

// AddStudent builds an SQL INSERT statement identical to the JavaScript version.
func AddStudent(name string, age int) string {
	return fmt.Sprintf(
		"INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
		name,
		age,
	)
}

/*
func main() {
	// Example usage:
	// sql := AddStudent("XXXXX", 0)
	// fmt.Println(sql)
}
*/