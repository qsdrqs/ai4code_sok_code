package main

import (
	"os"
)

// GetFilePath takes as input a string path representing a file path.
// It returns an open file handle if the path points to a regular file, otherwise nil.
func GetFilePath(path string) *os.File {
	info, err := os.Stat(path)
	if err != nil {
		return nil
	}
	if !info.Mode().IsRegular() {
		return nil
	}
	file, err := os.Open(path)
	if err != nil {
		return nil
	}
	return file
}