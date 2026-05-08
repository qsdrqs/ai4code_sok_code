package main

import (
	"errors"
	"os"
	"path/filepath"
	"strings"
)

const SafeDir = "/safedir"

func getFile(path string) (*os.File, error) {
	if !strings.HasPrefix(filepath.Clean(path), SafeDir) {
		return nil, errors.New("Path must be in SAFE_DIR")
	}
	return os.Open(path)
}