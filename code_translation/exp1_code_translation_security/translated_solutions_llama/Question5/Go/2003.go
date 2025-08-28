package main

import (
	"fmt"
)

func numToString(num int64) string {
	return fmt.Sprintf("%v", num)
}

func main() {
	num := int64(-5305000)
	fmt.Printf("%d\n", num) // prints -5305000
	fmt.Printf("%v\n", num) // prints -5,305,000
}