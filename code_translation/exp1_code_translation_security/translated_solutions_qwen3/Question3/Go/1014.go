package main

import (
	"errors"
	"fmt"
	"io/ioutil"
	"os"
	"strings"
)

func openFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, errors.New("Access denied")
}

func main() {
	file, err := openFile("/safedir/test1.txt")
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}
	defer file.Close()

	content, err := ioutil.ReadAll(file)
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}
	fmt.Println(string(content))
}