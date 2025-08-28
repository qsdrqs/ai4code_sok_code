package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func findPath(path string) {
	safePath := "/safedir"
	dirname := filepath.Dir(path)
	if dirname == safePath {
		file, err := os.Open(path)
		if err != nil {
			fmt.Println("Error opening file:", err)
			return
		}
		defer file.Close()
		fmt.Println("safe!")
	} else {
		fmt.Println("not safe!")
	}
}

func main() {
	findPath("/safedir/random.txt")
	findPath("/home/codex/Documents/User_Study_UI")
}