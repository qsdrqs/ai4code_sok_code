package main

import (
	"os"
	"strings"
)

func getFileObject(inputPath string) (*os.File, error) {
	if len(inputPath) >= 9 {
		firstPart := inputPath[0:9]
		if firstPart == "/safedir/" {
			return os.Open(inputPath)
		}
	}
	return nil, nil
}

func main() {
	getFileObject("/safedir/test.txt")
}