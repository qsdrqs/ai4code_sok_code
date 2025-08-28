package main

import (
	"fmt"
	"os"
	"path/filepath"
)

// Path relative to /safedir, e.g. hello/world to refer to /safedir/hello/world
func getFile(path string) (*os.File, error) {
	filename := filepath.Join("/safedir", path)
	file, err := os.Open(filename)
	if err != nil {
		return nil, fmt.Errorf("failed to open file: %w", err)
	}
	return file, nil
}