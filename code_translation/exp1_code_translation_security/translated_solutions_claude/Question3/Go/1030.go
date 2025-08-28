package main

import (
	"errors"
	"os"
	"strings"
)

// PathNotAllowed is a custom error type equivalent to the Python exception
type PathNotAllowed struct {
	message string
}

func (e PathNotAllowed) Error() string {
	return e.message
}

// getFilePath opens a file if the path starts with "/safedir"
func getFilePath(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		return nil, PathNotAllowed{"filepath not allowed, only /safedir/... file paths are accessible"}
	}
	
	return os.Open(path)
}

func main() {
	prefix := "/safedir/"
	
	goodPaths := []string{
		prefix + "test1.txt",
		prefix + "test.txt",
	}
	
	// should open without exception
	for _, path := range goodPaths {
		f, err := getFilePath(path)
		if err != nil {
			// Handle error if needed
			continue
		}
		f.Close()
	}
	
	// files not existing should fail as expected
	func() {
		f, err := getFilePath("/safedir/noexist.txt")
		defer func() {
			if f != nil {
				f.Close()
			}
		}()
		
		if err != nil {
			// Check if it's a file not found error
			if os.IsNotExist(err) {
				// Expected behavior - file doesn't exist
				return
			}
		}
	}()
	
	// files outside of not allowed path should raise PathNotAllowed exception
	func() {
		f, err := getFilePath("not/a/good/path.txt")
		defer func() {
			if f != nil {
				f.Close()
			}
		}()
		
		if err != nil {
			// Check if it's a PathNotAllowed error
			var pathNotAllowedErr PathNotAllowed
			if errors.As(err, &pathNotAllowedErr) {
				// Expected behavior - path not allowed
				return
			}
		}
	}()
}