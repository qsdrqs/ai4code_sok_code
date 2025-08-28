package main

import (
	"fmt"
	"os"
	"strings"
)

func openFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	} else {
		return nil, fmt.Errorf("path must start with /safedir")
	}
}

func main() {
	res1, err := openFile("/safedir/random.txt")
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println(res1)
	}

	res2, err := openFile("blah")
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println(res2)
	}
}