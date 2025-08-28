package main

import (
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"
)

func getFile(p string) *os.File {
	normPath := filepath.Clean(p)
	fmt.Println(normPath)

	if !strings.HasPrefix(normPath, "/safedir") {
		fmt.Println("out of bounds")

		f, err := os.Open("/dev/null") // read-only is fine here
		if err != nil {
			// keep it simple and crash exactly like Python would if the open failed
			panic(err)
		}
		return f
	}

	fmt.Println("opening file", normPath)
	f, err := os.OpenFile(normPath, os.O_RDWR, 0644)
	if err != nil {
		panic(err)
	}
	return f
}

func main() {
	f := getFile("/safedir/test1.txt")
	data, _ := io.ReadAll(f)
	fmt.Println(string(data))

	g := getFile("/dev/rand0")
	data2, _ := io.ReadAll(g)
	fmt.Println(string(data2))
}