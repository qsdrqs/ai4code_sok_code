package main

import (
	"fmt"
	"net/http"
	"strings"
)

/* Go function that takes as input a string name and an int age and adds a new entry
   to an SQL table named "STUDENTS" with columns for 'NAME' and 'AGE' */
func addStudent(name string, age int) {
	sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)

	request, err := http.NewRequest("POST", "http://localhost:8080/sql", strings.NewReader(sql))
	if err != nil {
		return
	}
	request.Header.Set("Content-Type", "application/x-www-form-urlencoded")

	client := &http.Client{}
	resp, err := client.Do(request)
	if err != nil {
		return
	}
	defer resp.Body.Close()
}