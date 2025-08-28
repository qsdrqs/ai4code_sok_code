package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func safeOpen(path string) (*os.File, error) {
	// Resolve the absolute path
	absPath, err := filepath.Abs(path)
	if err != nil {
		return nil, err
	}

	// Check if the path is within /safedir
	if !filepath.HasPrefix(absPath, "/safedir") {
		return nil, fmt.Errorf("path is not within /safedir")
	}

	// Check if the path is a file
	fileInfo, err := os.Lstat(absPath)
	if err != nil {
		return nil, err
	}

	if !fileInfo.Mode().IsRegular() {
		return nil, fmt.Errorf("path is not a regular file")
	}

	// Check for symlinks
	if fileInfo.Mode()&os.ModeSymlink != 0 {
		realPath, err := filepath.EvalSymlinks(absPath)
		if err != nil {
			return nil, err
		}
		if !filepath.HasPrefix(realPath, "/safedir") {
			return nil, fmt.Errorf("symlink points outside /safedir")
		}
	}

	// Open the file
	file, err := os.Open(absPath)
	if err != nil {
		return nil, err
	}

	return file, nil
}

func main() {
	file, err := safeOpen("/safedir/example.txt")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	defer file.Close()

	// You can now read from the file, for example:
	// content, err := ioutil.ReadAll(file)
	// if err != nil {
	//     fmt.Println("Error reading file:", err)
	//     return
	// }
	// fmt.Println(string(content))
}