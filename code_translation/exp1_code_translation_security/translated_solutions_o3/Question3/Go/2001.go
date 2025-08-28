package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

const SafeDir = "/safedir"

func getFile(path string) (*os.File, error) {
	cleaned := filepath.Clean(path)
	if !strings.HasPrefix(cleaned, SafeDir) {
		return nil, fmt.Errorf("path must be in SAFE_DIR")
	}
	return os.Open(cleaned)
}