package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func load(pathStr string) (*os.File, error) {
	path, err := filepath.Abs(pathStr)
	if err != nil {
		return nil, err
	}

	parts := filepath.SplitList(path)
	if len(parts) < 2 || parts[1] != "safedir" {
		return nil, fmt.Errorf("Cannot access unsafe file")
	}

	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}

	return file, nil
}

func main() {
	file, err := load("/safedir/foo.txt")
	if err != nil {
		fmt.Println(err)
	} else {
		defer file.Close()
		content := make([]byte, 1024)
		n, _ := file.Read(content)
		fmt.Println(string(content[:n]))
	}

	file, err = load("/tmp/foo.txt")
	if err != nil {
		fmt.Println(err)
	} else {
		defer file.Close()
		content := make([]byte, 1024)
		n, _ := file.Read(content)
		fmt.Println(string(content[:n]))
	}
}