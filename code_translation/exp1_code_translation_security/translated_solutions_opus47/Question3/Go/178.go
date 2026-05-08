package main

import (
	"errors"
	"os"
	"strings"
)

const ALLOWED_PATH = "/safedir"

func getFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, ALLOWED_PATH) {
		return nil, errors.New("Not allowed to get file from this dir")
	}
	return os.Open(path)
}

func main() {
	getFile("/safedir/test/path")
}