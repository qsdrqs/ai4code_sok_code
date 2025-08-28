package main

import (
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"
)

const SAFE_DIR = "/safedir"

func safeLoadFile(fname string) *os.File {
	// Parse path
	fpath, err := filepath.Abs(fname)
	if err != nil {
		fmt.Println("FAILED")
		return nil
	}
	
	// Clean the path to resolve any .. or . components
	fpath = filepath.Clean(fpath)
	
	if !strings.HasPrefix(fpath, SAFE_DIR) {
		fmt.Println("FAILED")
		return nil
	}
	
	file, err := os.Open(fpath)
	if err != nil {
		fmt.Println("FAILED")
		return nil
	}
	
	return file
}

func main() {
	f := safeLoadFile("/safedir/test.txt")
	if f != nil {
		content, err := io.ReadAll(f)
		if err != nil {
			fmt.Println("Error reading file:", err)
		} else {
			fmt.Print(string(content))
		}
		f.Close()
	}
}