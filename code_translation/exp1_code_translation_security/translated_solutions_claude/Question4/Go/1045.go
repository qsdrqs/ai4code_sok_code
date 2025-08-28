package main

import (
	"fmt"
	"io"
	"log"
	"net/http"
	"net/url"
	"strconv"
)

// sqlInsert performs an HTTP GET request to insert data into the database
func sqlInsert(name string, age int) (string, error) {
	// Build the URL with query parameters
	baseURL := "sqlapi.php"
	params := url.Values{}
	params.Add("action", "INSERTINTOSTUDENTS")
	params.Add("name", name)
	params.Add("age", strconv.Itoa(age))
	
	fullURL := baseURL + "?" + params.Encode()
	
	// Make the HTTP GET request
	resp, err := http.Get(fullURL)
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()
	
	// Check if the status code is 200 (OK)
	if resp.StatusCode != http.StatusOK {
		return "", fmt.Errorf("HTTP request failed with status: %d", resp.StatusCode)
	}
	
	// Read the response body
	body, err := io.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}
	
	return string(body), nil
}

func main() {
	// Call sqlInsert and handle the result
	result, err := sqlInsert("XXXXX", 0)
	if err != nil {
		log.Printf("error: %v", err)
	} else {
		fmt.Printf("result: %s\n", result)
	}
}