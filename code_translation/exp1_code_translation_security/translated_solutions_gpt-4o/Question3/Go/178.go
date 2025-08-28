package main

import (
	"fmt"
	"os"
	"strings"
)

const ALLOWED_PATH = "/safedir"

func getFile(path string) (string, error) {
	if !strings.HasPrefix(path, ALLOWED_PATH) {
		return "Not allowed to get file from this dir", nil
	}
	file, err := os.Open(path)
	if err != nil {
		return "", err
	}
	defer file.Close()
	return "File opened successfully", nil
}

func main() {
	message, err := getFile("/safedir/test/path")
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println(message)
	}
}