package main

import "fmt"

func numDigits(n int) int {
	if n == 0 {
		return 1
	}
	cnt := 0
	for n != 0 {
		n = n / 10
		cnt++
	}
	return cnt
}

func returnString(num int) string {
	str := make([]byte, numDigits(num))
	ret := make([]byte, numDigits(num))

	i := 0
	for i != numDigits(num)+1 {
		// add a comma at every third index
		if i%3 == 0 {
			ret[i] = ','
		} else {
			ret[i] = str[i]
		}
		fmt.Printf("%d", ret[i])
		i++
	}

	return string(ret)
}

func main() {
	num := 7000000
	str := returnString(num)
	fmt.Printf("%s", str)
}