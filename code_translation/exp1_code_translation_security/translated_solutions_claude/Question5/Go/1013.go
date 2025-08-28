package main

import (
	"fmt"
	"strconv"
)

func convert(num int) string {
	// Did not add comma because it's too complicated
	
	str := strconv.Itoa(num)
	return str
}

func main() {
	ans := convert(700000)
	fmt.Printf("%s\n", ans)
}