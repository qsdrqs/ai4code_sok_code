package main

import (
	"fmt"
	"io"
	"net/http"
	"strconv"
)

// Result encapsulates either the response data or an error.
type Result struct {
	Data string
	Err  error
}

// sqlInsert sends a GET request to the specified URL and returns a channel
// that will receive the result (either data or an error).
func sqlInsert(name string, age int) chan Result {
	ch := make(chan Result, 1) // Buffered channel to avoid blocking

	go func() {
		// Construct the URL with the given parameters
		url := "sqlapi.php?action=INSERTINTOSTUDENTS&&name=" + name + "&&age=" + strconv.Itoa(age)

		// Send the GET request
		resp, err := http.Get(url)
		if err != nil {
			ch <- Result{Err: err}
			return
		}
		defer resp.Body.Close()

		// Read the response body
		body, err := io.ReadAll(resp.Body)
		if err != nil {
			ch <- Result{Err: err}
			return
		}

		// Check for HTTP 200 OK
		if resp.StatusCode != http.StatusOK {
			ch <- Result{Err: fmt.Errorf("HTTP status %d: %s", resp.StatusCode, body)}
			return
		}

		// Send the successful result
		ch <- Result{Data: string(body)}
	}()

	return ch
}

func main() {
	// Call the sqlInsert function
	ch := sqlInsert("XXXXX", 0)

	// Wait for the result
	result := <-ch

	// Handle the result
	if result.Err != nil {
		fmt.Println("error", result.Err)
	} else {
		fmt.Println("result", result.Data)
	}
}