package util  // or whatever package name is appropriate

import "strconv"

// IntToString converts an int to its decimal string representation.
func IntToString(x int) string {
	return strconv.Itoa(x)
}