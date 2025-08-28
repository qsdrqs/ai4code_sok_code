package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

// sqlInsert launches the HTTP request in a goroutine and
// returns two channels: one for the result, one for errors.
func sqlInsert(name string, age int) (<-chan string, <-chan error) {
	result := make(chan string, 1)
	errCh  := make(chan error, 1)

	go func() {
		url := fmt.Sprintf(
			"sqlapi.php?action=INSERTINTOSTUDENTS&&name=%s&&age=%d",
			name, age,
		)

		resp, err := http.Get(url)
		if err != nil {
			errCh <- err
			return
		}
		defer resp.Body.Close()

		if resp.StatusCode != http.StatusOK {
			errCh <- fmt.Errorf("unexpected status: %s", resp.Status)
			return
		}

		body, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			errCh <- err
			return
		}

		result <- string(body)
	}()

	return result, errCh
}

func main() {
	resCh, errCh := sqlInsert("XXXXX", 0)

	select {
	case res := <-resCh:
		fmt.Println("result", res)
	case err := <-errCh:
		fmt.Println("error", err)
	}
}