package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"log"
	"net/http"
	"net/url"
)

// Student represents the data structure for a student
type Student struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

// insertStudent sends a POST request to add a new student entry
func insertStudent(name string, age int) error {
	// Create form data
	data := url.Values{}
	data.Set("name", name)
	data.Set("age", fmt.Sprintf("%d", age))

	// Create the request
	resp, err := http.Post("http://localhost:8080/students", 
		"application/x-www-form-urlencoded", 
		bytes.NewBufferString(data.Encode()))
	
	if err != nil {
		return fmt.Errorf("failed to send request: %v", err)
	}
	defer resp.Body.Close()

	// Read the response
	body, err := io.ReadAll(resp.Body)
	if err != nil {
		return fmt.Errorf("failed to read response: %v", err)
	}

	// Log the response (equivalent to console.log)
	fmt.Printf("Response: %s\n", string(body))
	
	return nil
}

// Alternative implementation using JSON instead of form data
func insertStudentJSON(name string, age int) error {
	student := Student{
		Name: name,
		Age:  age,
	}

	jsonData, err := json.Marshal(student)
	if err != nil {
		return fmt.Errorf("failed to marshal JSON: %v", err)
	}

	resp, err := http.Post("http://localhost:8080/students", 
		"application/json", 
		bytes.NewBuffer(jsonData))
	
	if err != nil {
		return fmt.Errorf("failed to send request: %v", err)
	}
	defer resp.Body.Close()

	body, err := io.ReadAll(resp.Body)
	if err != nil {
		return fmt.Errorf("failed to read response: %v", err)
	}

	fmt.Printf("Response: %s\n", string(body))
	
	return nil
}

func main() {
	// Call the function (equivalent to the JavaScript call)
	err := insertStudent("XXXXX", 0)
	if err != nil {
		log.Printf("Error inserting student: %v", err)
	}

	// Alternative JSON version
	err = insertStudentJSON("YYYYY", 25)
	if err != nil {
		log.Printf("Error inserting student (JSON): %v", err)
	}
}