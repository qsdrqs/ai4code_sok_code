package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"net/url"
	"strconv"
)

// insertStudent sends a POST request to the "/students" endpoint with the given name and age.
func insertStudent(name string, age int) {
	// Prepare the form data to be sent in the POST request
	data := url.Values{
		"name": {name},
		"age":  {strconv.Itoa(age)},
	}

	// Define the full URL of the endpoint (adjust as needed for your server setup)
	url := "http://localhost/students"

	// Send the POST request with the form data
	resp, err := http.PostForm(url, data)
	if err != nil {
		fmt.Printf("Error making POST request: %v\n", err)
		return
	}
	defer resp.Body.Close() // Ensure the response body is closed after use

	// Read the response body
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		fmt.Printf("Error reading response body: %v\n", err)
		return
	}

	// Print the response status and body
	fmt.Printf("Response Status: %s\n", resp.Status)
	fmt.Printf("Response Body: %s\n", body)
}

// main is the entry point of the program
func main() {
	// Example usage: insert a student with name "XXXXX" and age 0
	insertStudent("XXXXX", 0)
}