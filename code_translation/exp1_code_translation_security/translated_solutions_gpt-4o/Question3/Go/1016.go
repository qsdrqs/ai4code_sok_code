package main

import (
	"fmt"
	"os"
	"strings"
)

func pathToFile(path string) *os.File {
	// Check if the path contains '/safedir'
	if strings.Contains(path, "/safedir") {
		// Open the file in read-only mode
		file, err := os.Open(path)
		if err != nil {
			fmt.Println("Error opening file:", err)
			return nil
		}
		return file
	}
	return nil
}

func main() {
	// Test the pathToFile function
	file1 := pathToFile("/safedir/random.txt")
	if file1 != nil {
		fmt.Println("File opened successfully:", file1.Name())
		defer file1.Close()
	} else {
		fmt.Println("Failed to open file in /safedir")
	}

	file2 := pathToFile("/notsafedir/random.txt")
	if file2 != nil {
		fmt.Println("File opened successfully:", file2.Name())
		defer file2.Close()
	} else {
		fmt.Println("Failed to open file in /notsafedir")
	}
}