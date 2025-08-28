package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func openFile(path string) (*os.File, error) {
	absPath, err := filepath.Abs(path)
	if err != nil {
		return nil, err
	}

	safeDir, err := filepath.Abs("/safedir")
	if err != nil {
		return nil, err
	}

	if filepath.HasPrefix(absPath, safeDir) {
		return os.Open(path)
	} else {
		return nil, fmt.Errorf("Access Denied")
	}
}

func main() {
	file, err := openFile("/safedir/test.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()

	fmt.Println(file)
}