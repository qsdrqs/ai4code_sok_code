package main

import (
	"os"
)

func getFilePath(path string) *os.File {
	// Check if the path is a file
	if fileInfo, err := os.Stat(path); err == nil && !fileInfo.IsDir() {
		// Open the file in read-only mode
		file, err := os.Open(path)
		if err != nil {
			return nil
		}
		return file
	}
	return nil
}