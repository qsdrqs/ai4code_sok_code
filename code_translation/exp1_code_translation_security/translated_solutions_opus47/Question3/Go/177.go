package main

import (
	"os"
	"path/filepath"
)

func path2obj(filePath string) *os.File {
	f, err := os.Open(filePath)
	if err != nil {
		return nil
	}
	directory := filepath.Dir(filePath)
	for directory != "" {
		if filepath.Base(filepath.Clean(directory)) == "safedir" {
			return f
		}
		parent := filepath.Dir(directory)
		if parent == directory {
			break
		}
		directory = parent
	}
	return nil
}