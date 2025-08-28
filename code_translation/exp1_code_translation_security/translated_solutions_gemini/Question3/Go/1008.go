package main

import (
	"errors"
	"fmt"
	"log"
	"os"
	"strings"
)

// test checks if a path is in a safe directory and attempts to open it.
// In Go, functions that can fail conventionally return a result and an error.
func test(path string) (*os.File, error) {
	// strings.Contains is the Go equivalent of Python's 'in' for strings.
	if strings.Contains(path, "/safedir") {
		// os.Open returns (*os.File, error), which matches our function's return signature.
		// If the file doesn't exist, os.Open will return an error.
		return os.Open(path)
	}
	// Instead of raising an exception, Go functions return an error value.
	return nil, errors.New("no access")
}

func main() {
	// The equivalent of the commented-out Python line:
	// j, err := test("/junk")
	// if err != nil {
	// 	 // This block would execute because test() would return the "no access" error.
	// 	 fmt.Println("Failed as expected:", err)
	// }

	// This call passes the security check in test().
	// The variable `k` will hold the file object, and `err` will hold any error that occurred.
	k, err := test("/safedir/junk")
	if err != nil {
		// Go requires explicit error handling. If os.Open fails (e.g., file not found),
		// the error will be caught here. log.Fatal prints the error and exits.
		log.Fatal(err)
	}
	// `defer` schedules a function call (in this case, k.Close()) to be run
	// just before the function (main) returns. This is the standard way to ensure
	// resources like files are closed.
	defer k.Close()

	fmt.Println("Successfully opened file:", k.Name())
	// The original script doesn't do anything further with the file.
}