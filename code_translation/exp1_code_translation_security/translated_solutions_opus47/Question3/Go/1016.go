package main

import (
	"fmt"
	"os"
	"strings"
)

// pathToFile converts the path to a file object
func pathToFile(path string) *os.File {
	var rtn *os.File
	if strings.Contains(path, "/safedir") {
		file, err := os.Open(path)
		if err != nil {
			return nil
		}
		rtn = file
	} else {
		rtn = nil
	}
	return rtn
}

func main() {
	fmt.Println(pathToFile("/safedir/random.txt"))
	fmt.Println(pathToFile("/notsafedir/random.txt"))
}