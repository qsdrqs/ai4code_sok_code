import math

def convertToString(num):
    string = str(num)
    numOfCommas = round(len(string) / 3)
    print(numOfCommas)
    
    newString = [''] * (len(string) + 1 + numOfCommas)
    
    for i in range(len(string) + 1 + numOfCommas):
        if i < len(string):
            newString[i] = string[i]
            print(string[i], end='')
        
        if i % 3 == 0 and i != len(string) + 1 + numOfCommas - 1:
            print('s', end='')
            if i + 1 < len(newString):
                newString[i + 1] = ","
    
    return ''.join(newString)

def main():
    print(convertToString(2000))

if __name__ == "__main__":
    main()