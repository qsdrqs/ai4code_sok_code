package main

import (
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"
)

const SafeDir = "/safedir"

func safeLoadFile(fname string) *os.File {
	// Parse path
	fpath, err := filepath.EvalSymlinks(fname)
	if err != nil {
		panic(err)
	}
	if !strings.HasPrefix(fpath, SafeDir) {
		fmt.Println("FAILED")
		return nil
	}
	f, err := os.Open(fpath)
	if err != nil {
		panic(err)
	}
	return f
}

func main() {
	f := safeLoadFile("/safedir/test.txt")
	content, err := io.ReadAll(f)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(content))
	f.Close()
}