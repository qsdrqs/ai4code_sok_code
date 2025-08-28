package main

import (
	"os"
	"strings"
)

// readIn opens a file at the given path if it contains "/safedir"
func readIn(path string) (*os.File, error) {
	if !strings.Contains(path, "/safedir") {
		return nil, os.ErrPermissionDenied // or a custom error
	}
	return os.Open(path)
}

func main() {
	// Example usage:
	path := "/safedir/example.txt"
	file, err := readIn(path)
	if err != nil {
		// Handle the error
		return
	}
	defer file.Close()
	// Use the file
}