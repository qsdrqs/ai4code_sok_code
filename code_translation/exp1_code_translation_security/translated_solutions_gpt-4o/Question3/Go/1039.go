package main

import (
	"fmt"
	"os"
	"strings"
)

func returnFile(path string) *os.File {
	if !strings.HasPrefix(path, "/safedir") {
		fmt.Println("File cannot be accessed")
		return nil
	}
	file, err := os.Open(path)
	if err != nil {
		fmt.Println("Error opening file:", err)
		return nil
	}
	return file
}

func main() {
	// Example usage
	file := returnFile("/safedir/example.txt")
	if file != nil {
		defer file.Close()
		// Do something with the file
	}
}