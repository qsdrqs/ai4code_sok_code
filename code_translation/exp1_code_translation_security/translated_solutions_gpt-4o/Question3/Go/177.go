package main

import (
	"bufio"
	"os"
	"path/filepath"
	"strings"
)

func path2obj(filePath string) *os.File {
	// Open the file
	f, err := os.Open(filePath)
	if err != nil {
		return nil
	}

	// Get the directory of the file
	directory := filepath.Dir(filePath)

	// Traverse up the directory tree
	for directory != "" {
		// Check if the current directory is "safedir"
		if filepath.Base(filepath.Clean(directory)) == "safedir" {
			return f
		}
		// Move up one directory
		directory = filepath.Dir(directory)
	}

	// Close the file if "safedir" is not found
	f.Close()
	return nil
}

func main() {
	// Example usage
	filePath := "path/to/your/file.txt"
	file := path2obj(filePath)
	if file != nil {
		defer file.Close()
		scanner := bufio.NewScanner(file)
		for scanner.Scan() {
			line := scanner.Text()
			// Process the file line by line
			println(line)
		}
	}
}