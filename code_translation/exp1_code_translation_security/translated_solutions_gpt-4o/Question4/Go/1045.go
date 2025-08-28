package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"net/url"
)

// sqlInsert function to perform the HTTP GET request
func sqlInsert(name string, age int) (string, error) {
	// Construct the URL with query parameters
	baseURL := "sqlapi.php"
	params := url.Values{}
	params.Add("action", "INSERTINTOSTUDENTS")
	params.Add("name", name)
	params.Add("age", fmt.Sprintf("%d", age))

	// Combine base URL and query parameters
	fullURL := fmt.Sprintf("%s?%s", baseURL, params.Encode())

	// Perform the HTTP GET request
	resp, err := http.Get(fullURL)
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()

	// Check if the HTTP status code is 200
	if resp.StatusCode != http.StatusOK {
		return "", fmt.Errorf("HTTP request failed with status code: %d", resp.StatusCode)
	}

	// Read the response body
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}

	return string(body), nil
}

func main() {
	// Call the sqlInsert function
	result, err := sqlInsert("XXXXX", 0)
	if err != nil {
		fmt.Println("error:", err)
		return
	}

	// Print the result
	fmt.Println("result:", result)
}