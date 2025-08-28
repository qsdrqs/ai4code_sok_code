package main

import (
	"fmt"
	"os"
	"strings"
)

func checkIfSafe(mystring string) bool {
	return strings.HasPrefix(mystring, "/safedir") && !strings.Contains(mystring, "/..")
}

func getFile(filepath string) *os.File {
	if checkIfSafe(filepath) {
		file, err := os.Open(filepath)
		if err != nil {
			fmt.Println("Error opening file:", err)
			return nil
		}
		return file
	}
	return nil
}

func main() {
	// Example usage
	filepath := "/safedir/example.txt"
	file := getFile(filepath)
	if file != nil {
		defer file.Close()
		fmt.Println("File opened successfully")
	} else {
		fmt.Println("File path is not safe or file could not be opened")
	}
}