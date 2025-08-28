package main

import (
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// SafeOpen returns a cleaned, absolute path to a file inside "/safedir".
// If the file does not exist, is not a plain file, or is not inside
// "/safedir", it returns an error. This includes checks to prevent symlink
// attacks that point outside of "/safedir".
//
// On success, it returns a safe, absolute path string that you can then
// use with functions like os.Open or os.ReadFile.
func SafeOpen(path string) (string, error) {
	// The safe directory where files are allowed to be accessed.
	const safeDir = "/safedir"

	// Get the absolute path of the safe directory for reliable comparison.
	absSafeDir, err := filepath.Abs(safeDir)
	if err != nil {
		// This is a configuration error and should not happen in a stable environment.
		return "", fmt.Errorf("cannot resolve absolute path for safe directory: %w", err)
	}

	// Resolve the input path to its absolute form.
	resolvedPath, err := filepath.Abs(path)
	if err != nil {
		return "", fmt.Errorf("failed to resolve absolute path: %w", err)
	}

	// IMPORTANT: Evaluate all symbolic links to get the final, canonical path.
	// This prevents symlink attacks (e.g., a symlink pointing to /etc/passwd).
	resolvedPath, err = filepath.EvalSymlinks(resolvedPath)
	if err != nil {
		// This error means the path or a symlink within it doesn't exist or is invalid.
		return "", fmt.Errorf("failed to evaluate symlinks: %w", err)
	}

	// Check if the fully resolved path is within our safe directory.
	// We append a path separator to the prefix to ensure we don't match
	// directories like "/safedir-other".
	if !strings.HasPrefix(resolvedPath, absSafeDir+string(os.PathSeparator)) {
		return "", errors.New("path is outside the safe directory")
	}

	// Get file information to check its type.
	info, err := os.Stat(resolvedPath)
	if err != nil {
		// The file might not exist or there could be a permission issue.
		return "", fmt.Errorf("cannot access path info: %w", err)
	}

	// Ensure the path points to a regular file, not a directory or other special file.
	if !info.Mode().IsRegular() {
		return "", errors.New("path is not a regular file")
	}

	// If all checks pass, return the safe, canonical path.
	return resolvedPath, nil
}