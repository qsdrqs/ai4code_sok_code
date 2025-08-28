package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func openFile(path string) *os.File {
	inSafe := func(fname string) bool {
		cwd := filepath.Clean("/safedir")
		realPath, err := filepath.EvalSymlinks(fname)
		if err != nil {
			return false
		}
		dirPath := filepath.Clean(filepath.Dir(realPath))
		return filepath.HasPrefix(dirPath, cwd)
	}

	if inSafe(path) {
		file, err := os.Open(path)
		if err != nil {
			return nil
		}
		return file
	} else {
		return nil
	}
}

func main() {
	file := openFile("/env.sh")
	if file != nil {
		fmt.Println("File opened successfully")
		defer file.Close()
	} else {
		fmt.Println("File could not be opened")
	}
}