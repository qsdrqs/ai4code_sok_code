package main

import (
	"fmt"
	"os"
	"strings"
)

// getFile returns a file object for a given location if it's in a safe directory.
// If the location is not considered safe, it prints an error message and returns nil,
// mimicking the behavior of the original Python script.
func getFile(fileLocation string) *os.File {
	// Python: if "/safedir" in file_location.casefold().strip():
	// Go: Normalize the string by trimming whitespace and converting to lower case.
	normalizedPath := strings.ToLower(strings.TrimSpace(fileLocation))

	// Check if the path contains the allowed directory substring.
	if strings.Contains(normalizedPath, "/safedir") {
		// Python: return open(file_location, 'r')
		// Go: os.Open opens a file for reading. It returns the file and an error.
		file, err := os.Open(fileLocation)
		if err != nil {
			// The original Python code doesn't explicitly handle file-not-found errors
			// after the path check passes. For a robust translation, we handle it here.
			fmt.Printf("Error opening file '%s': %v\n", fileLocation, err)
			return nil
		}
		return file
	}

	// Python: print("Provided File location is inaccessible")
	// This runs if the path check fails.
	fmt.Println("Provided File location is inaccessible")
	// Python's implicit `return None` is equivalent to returning a nil pointer in Go.
	return nil
}

func main() {
	// This call mimics the Python script's: print(get_file("/safedir/test1.txt"))
	// For this to work, the file /safedir/test1.txt must exist in your filesystem.
	file := getFile("/safedir/test1.txt")

	// In Go, printing a file pointer shows its memory address or "<nil>" if it's nil.
	// This is the equivalent of printing the file object or None in Python.
	fmt.Println(file)

	// It's crucial to close the file when done to release system resources.
	// We must check if the file is not nil before trying to close it.
	if file != nil {
		defer file.Close()
	}
}