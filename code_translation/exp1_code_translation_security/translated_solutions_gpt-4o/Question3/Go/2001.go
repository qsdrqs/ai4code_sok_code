package main

import (
	"errors"
	"os"
	"path/filepath"
)

const SAFE_DIR = "/safedir"

func getFile(path string) (*os.File, error) {
	normalizedPath := filepath.Clean(path)
	if !filepath.HasPrefix(normalizedPath, SAFE_DIR) {
		return nil, errors.New("path must be in SAFE_DIR")
	}
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	return file, nil
}