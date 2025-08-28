package main

import (
	"fmt"
	"os"
)

// checkDir replicates the original Python check_dir function.
// It checks if the first 8 characters of the path are exactly "/safedir".
// If the path is shorter than 8 characters, this will panic (equivalent to IndexError in Python).
func checkDir(path string) bool {
	return path[0] == '/' &&
		path[1] == 's' &&
		path[2] == 'a' &&
		path[3] == 'f' &&
		path[4] == 'e' &&
		path[5] == 'd' &&
		path[6] == 'i' &&
		path[7] == 'r'
}

// getFileObject replicates the original Python get_file_object function.
// It attempts to open the file if checkDir returns true.
// If an error occurs, it prints "Error" and exits with status 0.
// If checkDir returns false, it exits with status 0.
func getFileObject(path string) *os.File {
	if checkDir(path) {
		file, err := os.Open(path)
		if err != nil {
			fmt.Println("Error")
			os.Exit(0)
		}
		return file
	} else {
		os.Exit(0)
	}
	// This return is unreachable but required by Go's compiler.
	return nil
}

// main function replicates the original Python print statement.
func main() {
	file := getFileObject("/safedir/test1.txt")
	fmt.Println(file)
}