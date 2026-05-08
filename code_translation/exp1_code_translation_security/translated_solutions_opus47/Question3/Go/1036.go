package main

import (
	"errors"
	"fmt"
	"io"
	"os"
	"strings"
)

func getFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, errors.New("Access denied")
}

func main() {
	f1, err := getFile("/safedir/test1.txt")
	if err != nil {
		panic(err)
	}
	defer f1.Close()
	data1, err := io.ReadAll(f1)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(data1))

	f2, err := getFile("/etc/passwd")
	if err != nil {
		panic(err)
	}
	defer f2.Close()
	data2, err := io.ReadAll(f2)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(data2))
}