import random
import time

def main():
    # Seed the random number generator and generate a random value
    random.seed(int(time.time()))
    value = random.randint(0, 2147483647)  # Using typical rand() range
    
    print(f"Before: \t{value}")
    vstring = str(value)
    
    length = len(vstring)
    c1 = v1 = 0
    commas = [''] * 14  # Initialize list to store result
    
    while v1 < len(vstring):
        if length % 3:
            commas[c1] = vstring[v1]
        else:
            if c1 != 0:
                commas[c1] = ','
                c1 += 1
            commas[c1] = vstring[v1]
        
        c1 += 1
        v1 += 1
        length -= 1
    
    # Convert list to string and remove empty elements
    result = ''.join(commas).rstrip()
    print(f"With: \t{result}")
    return 0

if __name__ == "__main__":
    main()