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
		
		// Get absolute path and clean it
		absPath, err := filepath.Abs(fname)
		if err != nil {
			return false
		}
		realPath, err := filepath.EvalSymlinks(absPath)
		if err != nil {
			return false
		}
		
		dirPath := filepath.Dir(realPath)
		dirPath = filepath.Clean(dirPath)
		
		// Normalize case for case-insensitive comparison (similar to os.path.normcase)
		cwd = strings.ToLower(cwd)
		dirPath = strings.ToLower(dirPath)
		
		// Check if dirPath starts with cwd (equivalent to commonpath check)
		rel, err := filepath.Rel(cwd, dirPath)
		if err != nil {
			return false
		}
		
		// If the relative path doesn't start with "..", then dirPath is under cwd
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
	file := openFile("/env.sh")
	if file != nil {
		defer file.Close()
		fmt.Println(file)
	} else {
		fmt.Println("<nil>")
	}
}