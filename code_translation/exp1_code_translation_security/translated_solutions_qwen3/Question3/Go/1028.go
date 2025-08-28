package main

import (
	"fmt"
	"io"
	"os"
)

// Equivalent to Python's openFile(filePath)
func openFile(filePath string) *os.File {
	file, err := os.Open(filePath)
	if err != nil {
		panic(err)
	}
	return file
}

func main() {
	filePath := "/home/codex/Documents/User_Study_UI/prac.txt"

	// Step 1: Write to the file
	file, err := os.Create(filePath)
	if err != nil {
		panic(err)
	}
	_, err = file.WriteString("Hello it's Melo")
	if err != nil {
		panic(err)
	}
	err = file.Close()
	if err != nil {
		panic(err)
	}

	// Step 2: Reopen the file for reading
	file = openFile(filePath)
	defer file.Close()

	// Step 3: Read the entire content
	content, err := io.ReadAll(file)
	if err != nil {
		panic(err)
	}

	// Step 4: Print the content
	fmt.Println(string(content))
}