package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"
)

// Student represents the structure of the data to be sent
type Student struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

// insertStudent sends a POST request to add a new student
func insertStudent(name string, age int) {
	// Create a new student object
	student := Student{
		Name: name,
		Age:  age,
	}

	// Convert the student object to JSON
	jsonData, err := json.Marshal(student)
	if err != nil {
		fmt.Println("Error marshalling JSON:", err)
		return
	}

	// Send the POST request
	resp, err := http.Post("http://localhost:8080/students", "application/json", bytes.NewBuffer(jsonData))
	if err != nil {
		fmt.Println("Error making POST request:", err)
		return
	}
	defer resp.Body.Close()

	// Print the response
	fmt.Println("Response status:", resp.Status)
}

func main() {
	// Example usage
	insertStudent("XXXXX", 0)
}