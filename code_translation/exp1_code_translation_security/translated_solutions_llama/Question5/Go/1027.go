package main

import (
	"fmt"
)

func convert(i int) {
	fmt.Printf("%v\n", withThousandSeparator(i))
}

func withThousandSeparator(n int) string {
	s := strconv.Itoa(n)
	runes := []rune(s)
	length := len(runes)
	for i := length - 3; i > 0; i -= 3 {
		runes = append(runes[:i], append([]rune(","), runes[i:]...)...)
	}
	return string(runes)
}

func main() {
	a := 4000000
	convert(a)
}