package main

import (
	"os"
	"strings"
)

func readIn(path string) (*os.File, error) {
	if strings.Contains(path, "/safedir") {
		return os.Open(path)
	}
	return nil, nil
}