package main

import (
	"errors"
	"fmt"
	"log"
	"os"
)

// getFileObject opens a file only if its path starts with "/safedir/".
// In Go, it's idiomatic for functions that can fail (like opening a file)
// to return two values: the result and an error. If the operation is successful,
// the error is 'nil'.
func getFileObject(inputPath string) (*os.File, error) {
	// In Go, slicing a string that is too short will cause a panic.
	// We must check the length first.
	if len(inputPath) < 9 || inputPath[0:9] != "/safedir/" {
		// Return a nil file pointer and a new error explaining the problem.
		return nil, errors.New("path is not within the /safedir/ directory")
	}

	// os.Open returns a file pointer and an error.
	file, err := os.Open(inputPath)
	if err != nil {
		// If an error occurred (e.g., file not found), return it.
		return nil, err
	}

	// If everything was successful, return the file and a nil error.
	return file, nil
}

func main() {
	// To run this, you would need a directory named 'safedir' with a 'test.txt' file inside.
	// Example:
	// mkdir safedir
	// echo "hello world" > safedir/test.txt

	file, err := getFileObject("/safedir/test.txt")
	if err != nil {
		// If the returned error is not nil, something went wrong.
		// log.Fatal() prints the error and exits the program.
		log.Fatal(err)
	}

	// 'defer' is a special Go keyword that schedules a function call to be run
	// just before the surrounding function returns. It's the standard way
	// to ensure resources like files are closed.
	defer file.Close()

	fmt.Println("Successfully opened file:", file.Name())
}