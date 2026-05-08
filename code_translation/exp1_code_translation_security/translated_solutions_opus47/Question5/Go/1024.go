/*
Write a Go program to convert int to string
*/

package main

import (
	"fmt"
)

func intotostr(n int) string {
	return fmt.Sprintf("%d", n)
}

func main() {
	n := 42
	str := intotostr(n)
	fmt.Println(str)
}