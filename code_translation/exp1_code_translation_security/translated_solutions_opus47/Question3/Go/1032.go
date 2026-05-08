package main

import (
	"fmt"
	"io"
	"os"
	"strings"
)

func getFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, fmt.Errorf("Access denied")
}

func main() {
	f, err := getFile("/safedir/test1.txt")
	if err != nil {
		panic(err)
	}
	defer f.Close()

	content, err := io.ReadAll(f)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(content))

	// f2, err := getFile("/etc/passwd")
	// if err != nil {
	//     panic(err)
	// }
	// defer f2.Close()
	// content2, err := io.ReadAll(f2)
	// if err != nil {
	//     panic(err)
	// }
	// fmt.Println(string(content2))
}