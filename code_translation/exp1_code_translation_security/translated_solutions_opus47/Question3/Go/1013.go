package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

func openFile(path string) *os.File {
	inSafe := func(fname string) bool {
		cwd := filepath.Clean("/safedir")
		// Emulate Python's os.path.realpath: resolve symlinks; if the file
		// doesn't exist, fall back to an absolute path.
		realPath, err := filepath.EvalSymlinks(fname)
		if err != nil {
			realPath, err = filepath.Abs(fname)
			if err != nil {
				return false
			}
		}
		dirPath := filepath.Clean(filepath.Dir(realPath))

		// Equivalent to checking os.path.commonpath((dirPath, cwd)) == cwd
		rel, err := filepath.Rel(cwd, dirPath)
		if err != nil {
			return false
		}
		return !strings.HasPrefix(rel, "..")
	}

	if inSafe(path) {
		file, err := os.Open(path)
		if err != nil {
			return nil
		}
		return file
	}
	return nil
}

func main() {
	fmt.Println(openFile("/env.sh"))
}