package main

import (
	"os"
	"path/filepath"
)

// path2obj opens a file and checks if it's within a "safedir" directory.
// If it is, the function returns the file descriptor.
// Otherwise, it returns nil.
func path2obj(filePath string) (*os.File, error) {
	f, err := os.Open(filePath)
	if err != nil {
		return nil, err
	}

	directory := filepath.Dir(filePath)
	for directory != "" {
		base := filepath.Base(directory)
		if base == "safedir" {
			return f, nil
		}
		directory = filepath.Dir(directory)
	}

	// Close the file if it's not within a "safedir"
	f.Close()
	return nil, nil
}

func main() {
	// Example usage:
	filePath := "path/to/your/file.txt"
	f, err := path2obj(filePath)
	if err != nil {
		println(err.Error())
		return
	}
	if f != nil {
		defer f.Close() // Don't forget to close the file
		println("File is within a safedir")
	} else {
		println("File is not within a safedir")
	}
}