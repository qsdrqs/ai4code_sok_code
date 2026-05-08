import random
import time

def main():
    random.seed(int(time.time()))
    value = random.randint(0, 2147483647)  # simulates C's rand()

    print(f"Before: \t{value}")
    vstring = str(value)

    length = len(vstring)
    c1 = v1 = 0
    commas = []

    while v1 < len(vstring):
        if length % 3:
            commas.append(vstring[v1])
        else:
            if c1 != 0:
                commas.append(',')
                c1 += 1
            commas.append(vstring[v1])
        c1 += 1
        v1 += 1
        length -= 1

    print(f"with: \t{''.join(commas)}")
    return 0


if __name__ == "__main__":
    main()