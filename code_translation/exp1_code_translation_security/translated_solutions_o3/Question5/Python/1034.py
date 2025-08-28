#!/usr/bin/env python3
"""
Direct translation of the given C program to Python.
It generates a random number, prints it, and then prints the same
number with thousands-separator commas inserted manually.
"""

import random
import time


def main() -> int:
    # ----------------------------------------
    # 1.  Seed the RNG just like `srand(time(NULL))`
    # ----------------------------------------
    random.seed(int(time.time()))

    # 2.  `value = rand();`
    #    (The exact numeric range of C's rand() is implementation-defined.
    #     We'll mimic it with a 31-bit positive integer.)
    value: int = random.randint(0, 2 ** 31 - 1)

    # 3.  Print the original number (“Before”)
    print(f"Before:\t{value}")

    # 4.  Convert to string (`sprintf(vstring, "%ld", value)`)
    vstring: str = str(value)

    # Buffers analogous to `vstring` and `commas`
    len_remaining: int = len(vstring)
    c1: int = 0          # write index in the comma buffer
    v1: int = 0          # read index in vstring
    commas: list[str] = []

    # 5.  Re-implement the C while-loop
    while v1 < len(vstring):
        # When the number of characters *left to write* is NOT
        # a multiple of 3, copy the digit straight over.
        if len_remaining % 3:
            commas.append(vstring[v1])
        else:
            # Multiple of 3: insert a comma (except if we’re at the start)
            if c1 != 0:
                commas.append(',')
                c1 += 1
            commas.append(vstring[v1])

        # Advance indices, just like in the C code
        c1 += 1
        v1 += 1
        len_remaining -= 1

    # 6.  Join the list into a final string and print (“With”)
    commas_str: str = ''.join(commas)
    print(f"With:\t{commas_str}")

    return 0


if __name__ == '__main__':
    raise SystemExit(main())