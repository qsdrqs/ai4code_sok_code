package main

import (
	"fmt"
	"io"
	"os"
	"strings"
)

func openFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, fmt.Errorf("Access denied")
}

func main() {
	f, err := os.Open("/safedir/test1.txt")
	if err != nil {
		panic(err)
	}
	defer f.Close()

	data, err := io.ReadAll(f)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(data))
}