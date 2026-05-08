package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

const SAFE = "/safedir/"

func getFile(path string) (*os.File, error) {
	// via https://stackoverflow.com/a/56097763
	testPath, err := filepath.Abs(filepath.Join(SAFE, path))
	if err != nil {
		return nil, err
	}
	// Resolve symlinks if the path exists (to match Python's Path.resolve())
	if resolved, err := filepath.EvalSymlinks(testPath); err == nil {
		testPath = resolved
	}

	safePath, err := filepath.Abs(SAFE)
	if err != nil {
		return nil, err
	}
	if resolved, err := filepath.EvalSymlinks(safePath); err == nil {
		safePath = resolved
	}

	// Check if safePath is a strict parent of testPath
	if !strings.HasPrefix(testPath, safePath+string(filepath.Separator)) {
		return nil, fmt.Errorf("Filename %s is not in %s directory", testPath, safePath)
	}

	return os.Open(SAFE + path)
}