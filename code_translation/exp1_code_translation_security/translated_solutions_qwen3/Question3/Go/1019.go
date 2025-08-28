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
		file, err := os.Open("/dev/null")
		if err != nil {
			panic(err)
		}
		return file
	} else {
		fmt.Printf("opening file %s\n", normpath)
		file, err := os.OpenFile(normpath, os.O_RDWR, 0)
		if err != nil {
			panic(err)
		}
		return file
	}
}

func main() {
	f := getFile("/safedir/test1.txt")
	data, err := io.ReadAll(f)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(data))

	g := getFile("/dev/rand0")
	data2, err := io.ReadAll(g)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(data2))
}