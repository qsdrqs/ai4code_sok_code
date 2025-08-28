package main

import (
	"fmt"
	"os"
	"strings"
)

// returnFile opens a file at the given path if it starts with '/safedir'
func returnFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		fmt.Println("File cannot be accessed")
		return nil, fmt.Errorf("file path does not start with /safedir")
	}
	return os.Open(path)
}

func main() {
	// Example usage:
	path := "/safedir/example.txt"
	file, err := returnFile(path)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()
}