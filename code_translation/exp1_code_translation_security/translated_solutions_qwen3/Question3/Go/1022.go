package main

import (
	"fmt"
	"os"
	"strings"
)

// get_file returns the file pointer if the location is accessible, else nil
func getFile(fileLocation string) *os.File {
	// Trim whitespace and convert to lowercase
	lowerAndTrimmed := strings.ToLower(strings.TrimSpace(fileLocation))
	// Check if the path contains "/safedir" (case-insensitive)
	if strings.Contains(lowerAndTrimmed, "/safedir") {
		// Open the file and return the pointer if successful
		file, err := os.Open(fileLocation)
		if err != nil {
			return nil
		}
		return file
	}
	// Print message if the location is inaccessible
	fmt.Println("Provided File location is inaccessible")
	return nil
}

func main() {
	// Example usage: print the file pointer
	file := getFile("/safedir/test1.txt")
	fmt.Println(file)
}