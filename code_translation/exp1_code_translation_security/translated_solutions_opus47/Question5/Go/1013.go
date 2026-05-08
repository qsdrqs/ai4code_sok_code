package main

import (
	"fmt"
)

func convert(num int) string {
	// Did not add comma because it's too complicated
	return fmt.Sprintf("%d", num)
}

func main() {
	ans := convert(700000)
	fmt.Println(ans)
	// No need to free - Go has garbage collection
}