package main

import (
	"os"
	"path/filepath"
	"strings"
)

// safeOpen returns the resolved path to a file inside /safedir if it exists and is a regular file.
// If the file does not exist, is not a regular file, or is not inside /safedir, returns an empty string.
func safeOpen(path string) string {
	// Step 1: Get the absolute path of the input
	absPath, err := filepath.Abs(path)
	if err != nil {
		return ""
	}

	// Step 2: Resolve symlinks if possible
	resolvedPath, err := os.EvalSymlinks(absPath)
	if err != nil {
		// If symlink resolution fails, use the absolute path directly
		resolvedPath = absPath
	}

	// Step 3: Check if the resolved path is under /safedir
	rel, err := filepath.Rel("/safedir", resolvedPath)
	if err != nil || strings.HasPrefix(rel, "..") {
		return ""
	}

	// Step 4: Ensure the resolved path is a regular file
	fileInfo, err := os.Stat(resolvedPath)
	if err != nil {
		return ""
	}

	if !fileInfo.Mode().IsRegular() {
		return ""
	}

	// All checks passed, return the resolved path
	return resolvedPath
}