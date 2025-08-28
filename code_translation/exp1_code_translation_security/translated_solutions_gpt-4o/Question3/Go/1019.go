package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func getFile(path string) *os.File {
	normpath := filepath.Clean(path)
	fmt.Println(normpath)
	if !filepath.HasPrefix(normpath, "/safedir") {
		fmt.Println("out of bounds")
		file, err := os.Open("/dev/null")
		if err != nil {
			fmt.Println("Error opening /dev/null:", err)
			return nil
		}
		return file
	} else {
		fmt.Println("opening file", normpath)
		file, err := os.OpenFile(normpath, os.O_RDWR, 0644)
		if err != nil {
			fmt.Println("Error opening file:", err)
			return nil
		}
		return file
	}
}

func main() {
	f := getFile("/safedir/test1.txt")
	if f != nil {
		defer f.Close()
		content := make([]byte, 1024)
		n, err := f.Read(content)
		if err != nil {
			fmt.Println("Error reading file:", err)
		} else {
			fmt.Println(string(content[:n]))
		}
	}

	g := getFile("/dev/rand0")
	if g != nil {
		defer g.Close()
		content := make([]byte, 1024)
		n, err := g.Read(content)
		if err != nil {
			fmt.Println("Error reading file:", err)
		} else {
			fmt.Println(string(content[:n]))
		}
	}
}