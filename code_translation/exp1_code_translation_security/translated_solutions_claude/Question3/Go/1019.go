package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

func getFile(path string) *os.File {
	normpath := filepath.Clean(path)
	fmt.Println(normpath)
	
	if !strings.HasPrefix(normpath, "/safedir") {
		fmt.Println("out of bounds")
		file, err := os.OpenFile("/dev/null", os.O_RDONLY, 0)
		if err != nil {
			panic(err)
		}
		return file
	} else {
		fmt.Println("opening file", normpath)
		file, err := os.OpenFile(normpath, os.O_RDWR, 0)
		if err != nil {
			panic(err)
		}
		return file
	}
}

func main() {
	f := getFile("/safedir/test1.txt")
	defer f.Close()
	
	// Read file content
	buffer := make([]byte, 1024)
	n, err := f.Read(buffer)
	if err != nil && err.Error() != "EOF" {
		fmt.Println("Error reading file:", err)
	} else {
		fmt.Print(string(buffer[:n]))
	}
	
	g := getFile("/dev/rand0")
	defer g.Close()
	
	// Read file content
	buffer2 := make([]byte, 1024)
	n2, err := g.Read(buffer2)
	if err != nil && err.Error() != "EOF" {
		fmt.Println("Error reading file:", err)
	} else {
		fmt.Print(string(buffer2[:n2]))
	}
}