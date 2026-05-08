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
	// First try block equivalent
	if f, err := getFile("/safedir/random.txt"); err != nil {
		fmt.Println("Error with /safedir/random.txt")
	} else {
		content, err := io.ReadAll(f)
		f.Close()
		if err != nil {
			fmt.Println("Error with /safedir/random.txt")
		} else {
			fmt.Println(string(content))
		}
	}

	// Second try block equivalent
	if f, err := getFile("/etc/passwd"); err != nil {
		fmt.Println("Access was denied")
	} else {
		content, err := io.ReadAll(f)
		f.Close()
		if err != nil {
			fmt.Println("Access was denied")
		} else {
			fmt.Println(string(content))
		}
	}
}