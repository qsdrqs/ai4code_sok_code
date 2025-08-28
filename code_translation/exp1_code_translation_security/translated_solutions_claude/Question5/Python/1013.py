def convert(num):
    # Did not add comma because it's too complicated
    
    # In Python, we don't need to calculate length or allocate memory manually
    # str() function handles the conversion directly
    str_result = str(num)
    return str_result

def main():
    ans = convert(700000)
    print(ans)
    # No need to free memory in Python - garbage collection handles it

if __name__ == "__main__":
    main()