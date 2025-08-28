package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

const SAFE_DIR = "/safedir"

func safeLoadFile(fname string) (*os.File, error) {
	// Parse path
	fpath, err := filepath.Abs(fname)
	if err != nil {
		fmt.Println("FAILED")
		return nil, err
	}

	if !strings.HasPrefix(fpath, SAFE_DIR) {
		fmt.Println("FAILED")
		return nil, fmt.Errorf("file not in safe directory")
	}

	file, err := os.Open(fpath)
	if err != nil {
		fmt.Println("FAILED")
		return nil, err
	}

	return file, nil
}

func main() {
	f, err := safeLoadFile("/safedir/test.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer f.Close()

	content := make([]byte, 1024)
	n, err := f.Read(content)
	if err != nil {
		fmt.Println("FAILED to read file")
		return
	}

	fmt.Println(string(content[:n]))
}