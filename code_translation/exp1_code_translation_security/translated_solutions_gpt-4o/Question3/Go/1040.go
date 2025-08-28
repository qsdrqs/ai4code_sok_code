package main

import (
	"fmt"
	"os"
	"strings"
)

// createFileObject creates a file object for the given path
func createFileObject(path string) *os.File {
	if strings.HasPrefix(path, "/safedir") {
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
	file := createFileObject("/etc/passwd")
	if file == nil {
		fmt.Println("File could not be opened or path is not allowed.")
	} else {
		defer file.Close()
		fmt.Println("File opened successfully:", file.Name())
	}
}