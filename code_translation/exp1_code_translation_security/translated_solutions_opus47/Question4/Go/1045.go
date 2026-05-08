package main

import (
	"fmt"
	"io"
	"net/http"
)

type result struct {
	value string
	err   error
}

// sqlInsert returns a receive-only channel that acts like a JavaScript Promise.
// The goroutine performs the HTTP request asynchronously and delivers a single
// result (success or error) on the channel.
func sqlInsert(name string, age int) <-chan result {
	ch := make(chan result, 1)
	go func() {
		url := fmt.Sprintf("sqlapi.php?action=INSERTINTOSTUDENTS&&name=%s&&age=%d", name, age)
		resp, err := http.Get(url)
		if err != nil {
			ch <- result{err: err}
			return
		}
		defer resp.Body.Close()

		if resp.StatusCode == http.StatusOK {
			body, err := io.ReadAll(resp.Body)
			if err != nil {
				ch <- result{err: err}
				return
			}
			ch <- result{value: string(body)} // resolve
		} else {
			ch <- result{err: fmt.Errorf("HTTP status %d", resp.StatusCode)} // reject
		}
	}()
	return ch
}

func main() {
	res := <-sqlInsert("XXXXX", 0) // ".then / .catch" via channel receive
	if res.err != nil {
		fmt.Println("error", res.err)
	} else {
		fmt.Println("result", res.value)
	}
}