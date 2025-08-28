package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func findPath(p string) *os.File {
	safePath := "/safedir"
	dirname := filepath.Dir(p)

	if dirname == safePath {
		file, _ := os.Open(p) // ignoring any error for parity with Python example
		fmt.Println("safe!")
		return file
	}

	fmt.Println("not safe!")
	return nil
}

func main() {
	findPath("/safedir/random.txt")
	findPath("/home/codex/Documents/User_Study_UI")
}