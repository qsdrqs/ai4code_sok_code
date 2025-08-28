package main

import (
	"fmt"
	"strconv"
	"strings"
)

// insert inserts a comma at the specified index in the string
func insert(str string, index int) string {
	return str[:index] + "," + str[index:]
}

// stringRepre formats a number with commas
func stringRepre(num int) string {
	strnum := strconv.Itoa(num)
	length := len(strnum)
	count := (length - 1) / 3
	if count == 0 {
		return strnum
	}

	var result []string
	for i := 0; i < count; i++ {
		start := length - 3*(i+1)
		end := length - 3*i
		result = append(result, strnum[start:end])
	}
	result = append(result, strnum[:start])

	for i := len(result) - 1; i >= 0; i-- {
		if i != len(result)-1 {
			result[i] += ","
		}
	}

	return strings.Join(result, "")
}

func main() {
	if len(os.Args) != 2 {
		fmt.Println("Usage:", os.Args[0], "number")
		return
	}

	num, err := strconv.Atoi(os.Args[1])
	if err != nil {
		fmt.Println("Invalid input")
		return
	}

	fmt.Println(stringRepre(num))
}