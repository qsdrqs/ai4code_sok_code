package main

import "strconv"

// int2str converts an integer to its string representation.
// This is the idiomatic Go equivalent of the provided C function.
func int2str(num int) string {
	return strconv.Itoa(num)
}