package main

import (
	"fmt"
	"os"
	"path/filepath"
)

const SAFE = "/safedir/"

func getFile(path string) (*os.File, error) {
	// Resolve the absolute path of the file
	testPath, err := filepath.Abs(filepath.Join(SAFE, path))
	if err != nil {
		return nil, err
	}

	// Resolve the absolute path of the safe directory
	safePath, err := filepath.Abs(SAFE)
	if err != nil {
		return nil, err
	}

	// Check if the testPath is within the safePath
	if !filepath.HasPrefix(testPath, safePath) {
		return nil, fmt.Errorf("filename %s is not in %s directory", testPath, safePath)
	}

	// Open the file for reading
	file, err := os.Open(testPath)
	if err != nil {
		return nil, err
	}

	return file, nil
}

func main() {
	// Example usage
	file, err := getFile("example.txt")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	defer file.Close()

	// Do something with the file
	fmt.Println("File opened successfully:", file.Name())
}