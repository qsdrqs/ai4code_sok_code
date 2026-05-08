package main

import (
	"errors"
	"os"
	"path/filepath"
	"strings"
)

const safeDir = "/safedir"

// getFile opens a file given a path relative to /safedir.
// e.g. "hello/world" refers to /safedir/hello/world.
func getFile(path string) (*os.File, error) {
	fullPath := filepath.Join(safeDir, path)
	// filepath.Join calls Clean, which resolves ".." elements — so a
	// path like "../etc/passwd" collapses to "/etc/passwd". We must
	// verify the cleaned result is still inside safeDir.
	if fullPath != safeDir && !strings.HasPrefix(fullPath, safeDir+string(filepath.Separator)) {
		return nil, errors.New("invalid path: outside of safe directory")
	}
	return os.Open(fullPath)
}