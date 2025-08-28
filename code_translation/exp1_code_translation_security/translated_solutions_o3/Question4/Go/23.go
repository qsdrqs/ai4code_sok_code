package main

import (
	"bytes"
	"fmt"
	"io"
	"log"
	"net/http"
	"net/url"
	"strconv"
)

// insertStudent sends a POST request to “/students”, passing the
// student’s name and age as `application/x-www-form-urlencoded` data
// (the same format jQuery’s $.ajax uses by default).
func insertStudent(name string, age int) error {
	form := url.Values{}
	form.Set("name", name)
	form.Set("age", strconv.Itoa(age))

	resp, err := http.Post(
		"http://localhost:8080/students",                      // adjust if your service lives elsewhere
		"application/x-www-form-urlencoded; charset=UTF-8",
		bytes.NewBufferString(form.Encode()),
	)
	if err != nil {
		return err
	}
	defer resp.Body.Close()

	// Echo whatever the service returned (purely for demonstration).
	body, _ := io.ReadAll(resp.Body)
	fmt.Println(string(body))

	return nil
}

func main() {
	if err := insertStudent("XXXXX", 0); err != nil {
		log.Fatalf("insert failed: %v", err)
	}
}