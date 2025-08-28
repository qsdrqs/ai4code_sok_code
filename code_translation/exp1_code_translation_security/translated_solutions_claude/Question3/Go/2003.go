package main

import (
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"
)

func load(pathStr string) (*os.File, error) {
	// Resolve the path (similar to Path.resolve() in Python)
	path, err := filepath.Abs(pathStr)
	if err != nil {
		return nil, err
	}
	
	// Clean the path to resolve any .. or . components
	path = filepath.Clean(path)
	
	// Split the path into parts
	parts := strings.Split(path, string(filepath.Separator))
	
	// Filter out empty parts (which can occur from leading separator)
	var filteredParts []string
	for _, part := range parts {
		if part != "" {
			filteredParts = append(filteredParts, part)
		}
	}
	
	// Check if path has at least 2 parts and second part is 'safedir'
	if len(filteredParts) < 2 || filteredParts[1] != "safedir" {
		return nil, fmt.Errorf("Cannot access unsafe file")
	}
	
	return os.Open(path)
}

func main() {
	// First call - should work
	file1, err1 := load("/safedir/foo.txt")
	if err1 != nil {
		fmt.Printf("Error: %v\n", err1)
	} else {
		content1, readErr1 := io.ReadAll(file1)
		file1.Close()
		if readErr1 != nil {
			fmt.Printf("Read error: %v\n", readErr1)
		} else {
			fmt.Print(string(content1))
		}
	}
	
	// Second call - should fail
	file2, err2 := load("/tmp/foo.txt")
	if err2 != nil {
		fmt.Printf("Error: %v\n", err2)
	} else {
		content2, readErr2 := io.ReadAll(file2)
		file2.Close()
		if readErr2 != nil {
			fmt.Printf("Read error: %v\n", readErr2)
		} else {
			fmt.Print(string(content2))
		}
	}
}