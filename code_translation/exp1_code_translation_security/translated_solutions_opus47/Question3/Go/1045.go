package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
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

	if strings.HasPrefix(absPath, safeDir) {
		return os.Open(path)
	}
	return nil, fmt.Errorf("Access Denied")
}

func main() {
	file, err := openFile("/safedir/test.txt")
	if err != nil {
		panic(err)
	}
	fmt.Println(file)
}