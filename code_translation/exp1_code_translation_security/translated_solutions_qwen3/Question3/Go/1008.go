package main

import (
	"errors"
	"os"
	"strings"
)

func test(path string) (*os.File, error) {
	if strings.Contains(path, "/safedir") {
		return os.Open(path)
	}
	return nil, errors.New("no access")
}

func main() {
	// j, err := test("/junk")
	// if err != nil {
	// 	panic(err)
	// }
	_, err := test("/safedir/junk")
	if err != nil {
		panic(err)
	}
}