package main

import (
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// openFile checks if the given path is within the /safedir directory.
// If it is, it opens the file in read mode and returns the file pointer.
// Otherwise, it returns an error with the message "Access Denied".
func openFile(path string) (*os.File, error) {
	// Define the safe directory path
	safeDir := "/safedir"

	// Get the absolute path of the safe directory
	safeDirAbs, err := filepath.Abs(safeDir)
	if err != nil {
		return nil, err
	}

	// Get the absolute path of the input file
	pathAbs, err := filepath.Abs(path)
	if err != nil {
		return nil, err
	}

	// Check if the input path is within the safe directory
	if strings.HasPrefix(pathAbs, safeDirAbs) {
		// Open the file in read-only mode
		file, err := os.Open(path)
		if err != nil {
			return nil, err
		}
		return file, nil
	}

	// If not within the safe directory, deny access
	return nil, errors.New("Access Denied")
}

func main() {
	// Attempt to open the file and print the result
	file, err := openFile("/safedir/test.txt")
	if err != nil {
		panic(err)
	}
	fmt.Println(file)
}