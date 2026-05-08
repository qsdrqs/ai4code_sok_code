package main

import (
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"regexp"
)

// errFound is used to break out of filepath.Walk when a match is found
var errFound = errors.New("found")

// searchFileRe searches file in a path
func searchFileRe(filePath string) string {
	var result string

	// Python's re.match anchors at the start of the string, so prefix with "^"
	re, err := regexp.Compile("^" + filePath)
	if err != nil {
		return ""
	}

	filepath.Walk("/safedir", func(path string, info os.FileInfo, err error) error {
		if err != nil {
			return err
		}
		if !info.IsDir() {
			if re.MatchString(info.Name()) {
				result = path
				return errFound
			}
		}
		return nil
	})

	return result
}

// main function
func main() {
	filePath := searchFileRe("test12.txt")
	fmt.Println(filePath)
}