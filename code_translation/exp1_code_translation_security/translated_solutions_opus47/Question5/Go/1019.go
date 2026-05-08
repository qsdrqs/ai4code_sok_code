package main

import (
	"fmt"
)

func stringRep(num int) *string {
	s := fmt.Sprintf("%d", num)
	return &s
}

func main() {
	leet := 1337
	leetStr := stringRep(leet)
	fmt.Printf("%s", *leetStr)
}