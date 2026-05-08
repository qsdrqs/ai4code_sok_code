package main

import (
	"fmt"
	"io"
	"net/http"
	"net/url"
	"strconv"
)

// There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE".
// This function takes a string name and an int age and sends a POST request
// to add a new entry to "STUDENTS" (matching the JS version's behavior).
func insertStudent(name string, age int) {
	data := url.Values{}
	data.Set("name", name)
	data.Set("age", strconv.Itoa(age))

	// Note: Go's HTTP client requires a full URL (unlike jQuery's $.ajax,
	// which resolves relative URLs against the page origin).
	resp, err := http.PostForm("/students", data)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer resp.Body.Close()

	body, err := io.ReadAll(resp.Body)
	if err != nil {
		fmt.Println(err)
		return
	}

	// Equivalent to success: function(response){ console.log(response); }
	fmt.Println(string(body))
}

func main() {
	insertStudent("XXXXX", 0)
}