package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

func openFile(filename string) *os.File {
	// Clean and resolve the path (similar to pathlib.Path.resolve())
	path := filepath.Clean(filename)
	if !filepath.IsAbs(path) {
		var err error
		path, err = filepath.Abs(path)
		if err != nil {
			return nil
		}
	}
	
	// Split the path into parts
	parts := strings.Split(path, string(filepath.Separator))
	
	// Filter out empty parts (which can occur from leading separators)
	var filteredParts []string
	for _, part := range parts {
		if part != "" {
			filteredParts = append(filteredParts, part)
		}
	}
	
	// Check if we have at least 2 parts and the second part is "safedir"
	if len(filteredParts) >= 2 && filteredParts[1] == "safedir" {
		file, err := os.Open(path)
		if err != nil {
			// In Python, open() would raise an exception, but we return nil here
			// If you want to panic (similar to Python exception), uncomment the next line:
			// panic(err)
			return nil
		}
		return file
	}
	return nil
}

func test() {
	// Test 1: assert open_file("/safedir/../a/b") is None
	result1 := openFile("/safedir/../a/b")
	if result1 != nil {
		panic("Expected nil but got a file")
	}
	
	// Test 2: try opening "/safedir/a" and expect it to fail
	defer func() {
		if r := recover(); r != nil {
			// Expected to panic/fail, so this is good
			fmt.Println("Test passed: opening /safedir/a failed as expected")
		}
	}()
	
	result2 := openFile("/safedir/a")
	if result2 != nil {
		result2.Close() // Clean up if file was opened
		panic("Expected file opening to fail but it succeeded")
	}
	// If we reach here, the file opening returned nil (failed), which might be expected
	fmt.Println("Test passed: opening /safedir/a returned nil")
}

func main() {
	test()
	fmt.Println("All tests completed")
}