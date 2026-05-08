package main

import "fmt"

func intToString(num int) string {
	str := make([]byte, 100)
	i := 0
	j := 0
	count := 0
	temp := num

	for temp != 0 {
		str[i] = byte(temp%10) + '0'
		temp = temp / 10
		i++
		count++
		if count == 3 && temp != 0 {
			str[i] = ','
			i++
			count = 0
		}
	}

	// Slice to the actual length used (no null terminator needed in Go)
	result := str[:i]
	i--

	// Reverse the slice in-place
	for j < i {
		result[j], result[i] = result[i], result[j]
		j++
		i--
	}

	return string(result)
}

func main() {
	fmt.Println(intToString(7000000))
}