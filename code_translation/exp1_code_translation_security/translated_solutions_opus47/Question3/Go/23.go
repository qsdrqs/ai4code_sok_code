package main

import (
	"fmt"
	"os"
	"strings"
)

func thing(path string) *os.File {
	if strings.Contains(path, "/safedir") {
		// continue
		f, err := os.Open(path)
		if err != nil {
			return nil
		}
		return f
	} else {
		// panic
		fmt.Println("invalid path")
		return nil
	}
}

func main() {
	// test good path
	f := thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt")
	fmt.Println("\nResult 1:")
	fmt.Println(f)

	// test bad path
	f = thing("/home/codex/Documents/User_Study_UI/random.txt")
	fmt.Println("\nResult 2:")
	fmt.Println(f)
}