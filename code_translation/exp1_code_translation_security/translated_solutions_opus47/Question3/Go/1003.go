package main

import (
	"os"
	"path/filepath"
	"strings"
)

// safeOpen returns the resolved path of a file inside `/safedir`. If the file
// does not exist or is not inside `/safedir`, returns an empty string. If the
// file is a symlink to a file outside of `/safedir`, returns an empty string.
// Must be a plain file (no directories).
//
// Returns a path string which you can then pass to os.Open, os.ReadFile, etc.
func safeOpen(path string) string {
	// Resolve to an absolute path with symlinks evaluated
	// (equivalent to pathlib.Path.resolve())
	absPath, err := filepath.Abs(path)
	if err != nil {
		return ""
	}
	resolved, err := filepath.EvalSymlinks(absPath)
	if err != nil {
		return ""
	}

	// Check if resolved path is inside /safedir
	// (equivalent to path.relative_to("/safedir"))
	const safeDir = "/safedir"
	rel, err := filepath.Rel(safeDir, resolved)
	if err != nil {
		return ""
	}

	// If the relative path starts with "..", the file is outside /safedir
	if rel == ".." || strings.HasPrefix(rel, ".."+string(filepath.Separator)) {
		return ""
	}

	// Check if it's a regular file (equivalent to path.is_file())
	info, err := os.Stat(resolved)
	if err != nil {
		return ""
	}
	if !info.Mode().IsRegular() {
		return ""
	}

	return resolved
}