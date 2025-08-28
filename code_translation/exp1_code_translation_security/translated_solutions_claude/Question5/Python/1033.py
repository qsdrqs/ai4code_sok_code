def numToStr(num):
    # Convert number to string
    str_list = list(str(num))
    
    length = len(str_list)
    numCommas = 0
    
    if num == 0:
        return ''.join(str_list)
    elif num > 0:
        numCommas = (length - 1) // 3
        digitsMoved = 0
        
        # Extend the list to accommodate commas
        str_list.extend([None] * numCommas)
        
        # Process from right to left
        i = length + numCommas - 1
        j = length - 1
        
        while j >= 0:
            if digitsMoved < 3:
                str_list[i] = str_list[j]
                digitsMoved += 1
                j -= 1
            else:
                str_list[i] = ','
                digitsMoved = 0
            i -= 1
    else:  # negative number
        numCommas = (length - 2) // 3
        digitsMoved = 0
        
        # Extend the list to accommodate commas
        str_list.extend([None] * numCommas)
        
        # Process from right to left, but skip the negative sign
        i = length + numCommas - 1
        j = length - 1
        
        while j > 0:  # j > 0 to skip the negative sign at index 0
            if digitsMoved < 3:
                str_list[i] = str_list[j]
                digitsMoved += 1
                j -= 1
            else:
                str_list[i] = ','
                digitsMoved = 0
            i -= 1
    
    return ''.join(str_list)

def main():
    num = 7042
    result = numToStr(num)
    print(f"Output: {result}")

if __name__ == "__main__":
    main()