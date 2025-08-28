package main

import (
	"fmt"
	"os"
	"strings"
)

// thing checks if a path contains "/safedir".
// If it does, it attempts to open the file and returns the file handle.
// If not, it prints an error message and returns nil.
func thing(path string) *os.File {
	if strings.Contains(path, "/safedir") {
		// #continue - This block corresponds to the success case.
		file, err := os.Open(path)
		// In Python, an unhandled error from open() would crash the program.
		// panic() is the Go equivalent for an unrecoverable state.
		if err != nil {
			panic(err)
		}
		return file
	} else {
		// #panic - The original comment suggests panicking. However, the Python
		// code's actual behavior is to print a message and return None,
		// allowing the program to continue. We replicate the behavior here.
		fmt.Println("invalid path")
		return nil // nil is Go's equivalent of None for pointer types.
	}
}

func main() {
	// NOTE: For the "good path" test to succeed, the file at that path must exist.
	// If it doesn't, the program will panic, which is the same behavior
	// as an unhandled FileNotFoundError in Python.

	// Test good path
	// We use a defer/recover to gracefully handle the potential panic from the first
	// call, allowing the program to demonstrate both test cases without crashing.
	defer func() {
		if r := recover(); r != nil {
			fmt.Printf("\nResult 1 (panic caught):\n%v\n", r)
		}
	}()

	f := thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt")
	fmt.Println("\nResult 1:")
	fmt.Println(f)
	// It's good practice to close the file handle when done.
	if f != nil {
		f.Close()
	}

	// Test bad path
	f = thing("/home/codex/Documents/User_Study_UI/random.txt")
	fmt.Println("\nResult 2:")
	fmt.Println(f) // This will print <nil>
}