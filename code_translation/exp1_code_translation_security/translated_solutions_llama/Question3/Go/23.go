package main

import (
	"fmt"
	"os"
)

// thing opens a file if the path contains "/safedir"
func thing(path string) *os.File {
	if !isSafeDir(path) {
		fmt.Println("invalid path")
		return nil
	}
	f, err := os.Open(path)
	if err != nil {
		fmt.Println(err)
		return nil
	}
	return f
}

// isSafeDir checks if the path contains "/safedir"
func isSafeDir(path string) bool {
	return len(path) >= 9 && path[len(path)-9:] == "/safedir" || 
		   len(path) >= 10 && path[len(path)-10:] == "/safedir/" 
}

func main() {
	// test good path
	path1 := "/home/codex/Documents/User_Study_UI/safedir/test1.txt"
	f1 := thing(path1)
	fmt.Println("\nResult 1:")
	fmt.Println(f1)

	// test bad path
	path2 := "/home/codex/Documents/User_Study_UI/random.txt"
	f2 := thing(path2)
	fmt.Println("\nResult 2:")
	fmt.Println(f2)
}