package main

import (
	"fmt"
	"os"
	"strings"
)

// converts the path to a file object
func pathToFile(path string) *os.File {
	if strings.Contains(path, "/safedir") {
		f, _ := os.Open(path) // ignore the error for parity with the Python example
		return f
	}
	return nil
}

func main() {
	fmt.Println(pathToFile("/safedir/random.txt"))
	fmt.Println(pathToFile("/notsafedir/random.txt"))
}