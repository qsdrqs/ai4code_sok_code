package main

import (
	"fmt"
	"io"
	"os"
)

// openFile replicates the Python `openFile` function and returns an *os.File.
func openFile(filePath string) (*os.File, error) {
	file, err := os.Open(filePath) // open read-only
	if err != nil {
		return nil, err
	}
	return file, nil
}

func main() {
	filePath := "/home/codex/Documents/User_Study_UI/prac.txt"

	// Write "Hello it's Melo" to the file (similar to Python's `with open(..., 'w') as f:`)
	f, err := os.Create(filePath) // create/truncate file and open for writing
	if err != nil {
		fmt.Println("error creating file:", err)
		return
	}
	if _, err := f.WriteString("Hello it's Melo"); err != nil {
		fmt.Println("error writing to file:", err)
		f.Close()
		return
	}
	f.Close() // close after writing

	// Open the file through our helper and read its contents
	file, err := openFile(filePath)
	if err != nil {
		fmt.Println("error opening file:", err)
		return
	}
	defer file.Close()

	data, err := io.ReadAll(file)
	if err != nil {
		fmt.Println("error reading file:", err)
		return
	}

	fmt.Println(string(data)) // prints: Hello it's Melo
}