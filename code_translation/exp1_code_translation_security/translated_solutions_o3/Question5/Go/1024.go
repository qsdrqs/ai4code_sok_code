// Write a Go program to convert an int into a string.
package main

import "strconv"

// intToStr converts an int to its decimal string representation.
func intToStr(n int) string {
	return strconv.Itoa(n)
}