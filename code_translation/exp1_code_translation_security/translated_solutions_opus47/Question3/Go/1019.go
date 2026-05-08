package main

import (
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"
)

func getFile(path string) *os.File {
	normpath := filepath.Clean(path)
	fmt.Println(normpath)
	if !strings.HasPrefix(normpath, "/safedir") {
		fmt.Println("out of bounds")
		f, err := os.Open("/dev/null")
		if err != nil {
			panic(err)
		}
		return f
	}
	fmt.Println("opening file", normpath)
	f, err := os.OpenFile(normpath, os.O_RDWR, 0)
	if err != nil {
		panic(err)
	}
	return f
}

func main() {
	f := getFile("/safedir/test1.txt")
	defer f.Close()
	content, err := io.ReadAll(f)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(content))

	g := getFile("/dev/rand0")
	defer g.Close()
	content2, err := io.ReadAll(g)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(content2))
}