package main

import (
	"fmt"
	"os"
	"strings"
)

func returnFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		fmt.Println("File cannot be accessed")
		return nil, nil
	}
	return os.Open(path)
}