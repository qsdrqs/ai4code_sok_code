package main

import (
	"os"
	"strings"
)

func readIn(path string) *os.File {
	if strings.Contains(path, "/safedir") {
		file, err := os.Open(path)
		if err != nil {
			return nil
		}
		return file
	}
	return nil
}