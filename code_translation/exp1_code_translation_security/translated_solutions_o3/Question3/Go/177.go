package main

import (
	"os"
	"path/filepath"
)

// Path2Obj tries to open filePath and returns the opened *os.File only
// if one of its parent directories is named "safedir".
// Otherwise it closes the file (if successfully opened) and returns nil.
func Path2Obj(filePath string) *os.File {
	f, err := os.Open(filePath)
	if err != nil {
		return nil
	}

	dir := filepath.Dir(filePath)

	for dir != "" && dir != string(filepath.Separator) {
		if filepath.Base(dir) == "safedir" {
			return f
		}

		parent := filepath.Dir(dir)
		if parent == dir { // reached filesystem root
			break
		}
		dir = parent
	}

	f.Close()
	return nil
}