package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

func openFile(filename string) (*os.File, error) {
	// Resolve the absolute path
	absPath, err := filepath.Abs(filename)
	if err != nil {
		return nil, err
	}

	// Split the path into parts
	parts := strings.Split(absPath, string(os.PathSeparator))

	// Check if the path is valid
	if len(parts) >= 2 && parts[1] == "safedir" {
		return os.Open(absPath)
	}
	return nil, nil
}

func test() {
	// Test case 1
	if file, _ := openFile("/safedir/../a/b"); file != nil {
		fmt.Println("Test case 1 failed")
	} else {
		fmt.Println("Test case 1 passed")
	}

	// Test case 2
	file, err := openFile("/safedir/a")
	if err == nil && file != nil {
		fmt.Println("Test case 2 failed")
	} else {
		fmt.Println("Test case 2 passed")
	}
}

func main() {
	test()
}