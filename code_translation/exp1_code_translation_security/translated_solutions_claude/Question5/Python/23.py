import math

def int_to_string(x):
    tempstring = str(x)
    
    # Calculate length (equivalent to the C math calculation)
    if x > 0:
        length = int(math.ceil(math.log10(x)) + 1)
    else:
        length = len(tempstring)
    
    result = ""
    
    # The original C logic seems incomplete, but based on the structure,
    # it appears to be trying to add commas every 3 digits
    # Here's a proper implementation of comma-separated number formatting:
    
    # Convert to string and add commas from right to left
    temp_result = ""
    for i, digit in enumerate(reversed(tempstring)):
        if i > 0 and i % 3 == 0:
            temp_result = "," + temp_result
        temp_result = digit + temp_result
    
    return temp_result

def main():
    print(int_to_string(100000), end="")
    print("test")

if __name__ == "__main__":
    main()