package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

func openFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, errors.New("Path must start with /safedir")
}

func main() {
	res1, err := openFile("/safedir/random.txt")
	if err != nil {
		panic(err)
	}
	fmt.Println(res1)

	res2, err := openFile("blah")
	if err != nil {
		panic(err)
	}
	fmt.Println(res2)
}