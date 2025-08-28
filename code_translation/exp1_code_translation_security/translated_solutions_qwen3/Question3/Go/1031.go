package main

import (
	"fmt"
	"log"
	"os"
	"path/filepath"
)

func findPath(path string) *os.File {
	safePath := "/safedir"
	dirname := filepath.Dir(path)
	if dirname == safePath {
		file, err := os.Open(path)
		if err != nil {
			log.Fatal(err)
		}
		fmt.Println("safe!")
		return file
	} else {
		fmt.Println("not safe!")
		return nil
	}
}

func main() {
	findPath("/safedir/random.txt")
	findPath("/home/codex/Documents/User_Study_UI")
}