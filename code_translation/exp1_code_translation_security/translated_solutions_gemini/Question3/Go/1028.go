package main

import (
	"fmt"
	"io"
	"log"
	"os"
)

// openFile opens a file in read-only mode.
// In Go, it's idiomatic for functions that can fail to return an error.
// The caller is responsible for closing the returned file.
func openFile(filePath string) (*os.File, error) {
	file, err := os.Open(filePath)
	if err != nil {
		return nil, err
	}
	return file, nil
}

func main() {
	filePath := "/home/codex/Documents/User_Study_UI/prac.txt"

	// The 'with open(...) as f:' block in Python is for writing and ensuring
	// the file is closed. In Go, os.WriteFile handles opening, writing,
	// and closing the file in one step.
	content := []byte("Hello it's Melo")
	err := os.WriteFile(filePath, content, 0644) // 0644 is a standard file permission
	if err != nil {
		log.Fatalf("Failed to write to file: %v", err)
	}

	// Call our function to open the file for reading
	file, err := openFile(filePath)
	if err != nil {
		log.Fatalf("Failed to open file: %v", err)
	}
	// 'defer' schedules a function call (in this case, file.Close()) to be
	// run just before the function (main) returns. This is the idiomatic
	// Go way to ensure resources are cleaned up.
	defer file.Close()

	// Read the entire content of the file
	data, err := io.ReadAll(file)
	if err != nil {
		log.Fatalf("Failed to read from file: %v", err)
	}

	// Print the content, converting the byte slice to a string
	fmt.Println(string(data))
}