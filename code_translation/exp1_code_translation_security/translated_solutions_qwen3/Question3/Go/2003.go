package main

import (
	"errors"
	"fmt"
	"io"
	"log"
	"os"
	"path/filepath"
	"strings"
)

// load validates the path and returns an open file handle if the path is safe.
func load(pathStr string) (*os.File, error) {
	// Resolve the absolute path
	absPath, err := filepath.Abs(pathStr)
	if err != nil {
		return nil, err
	}

	// Split the path into components
	parts := strings.Split(absPath, string(filepath.Separator))

	// Check if the second component is "safedir"
	if len(parts) < 2 || parts[1] != "safedir" {
		return nil, errors.New("Cannot access unsafe file")
	}

	// Open the file
	file, err := os.Open(absPath)
	if err != nil {
		return nil, err
	}

	return file, nil
}

func main() {
	// First test: should succeed if file exists and is under /safedir
	file1, err := load("/safedir/foo.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file1.Close()

	data1, err := io.ReadAll(file1)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(string(data1))

	// Second test: should fail due to invalid path
	file2, err := load("/tmp/foo.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file2.Close()

	data2, err := io.ReadAll(file2)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(string(data2))
}