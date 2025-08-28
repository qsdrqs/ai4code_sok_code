package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

func inSafe(fname string) bool {
	// Directory we consider “safe”.
	safeDir := filepath.Clean("/safedir")

	// Resolve symlinks, convert to an absolute, cleaned path.
	realPath, err := filepath.EvalSymlinks(fname)
	if err != nil {
		return false
	}
	realPath = filepath.Clean(realPath)

	// Directory that contains the requested file.
	dir := filepath.Dir(realPath)

	// Is dir inside safeDir?
	rel, err := filepath.Rel(safeDir, dir)
	if err != nil {
		return false
	}
	// rel starting with ".." means dir is outside safeDir.
	if rel == ".." || strings.HasPrefix(rel, ".."+string(os.PathSeparator)) {
		return false
	}
	return true
}

func openFile(path string) *os.File {
	if !inSafe(path) {
		return nil
	}

	f, err := os.Open(path)
	if err != nil {
		return nil
	}
	return f
}

func main() {
	fmt.Println(openFile("/env.sh"))
}