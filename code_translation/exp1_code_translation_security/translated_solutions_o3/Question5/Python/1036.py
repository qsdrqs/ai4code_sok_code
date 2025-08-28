"""
A direct translation of the given C program to Python.
All ‘dependencies’ (i.e. the standard library) are available by default in Python.
"""

def num_digits(n: int) -> int:
    # In C the author wrote  if(n = 0) which is an assignment, not a comparison.
    # Python needs a real comparison.
    if n == 0:
        return 1

    cnt = 0
    while n != 0:
        n //= 10          # integer division exactly as in C’s  n = n / 10;
        cnt += 1
    return cnt


def return_string(num: int) -> str:
    # The C code creates two fixed-size char arrays whose size is num_digits(num)
    # and keeps writing up to (num_digits(num) + 1) elements, which would
    # overflow in C.  To stay faithful but remain valid Python, we size them one
    # element larger.
    size = num_digits(num) + 1              # +1 because the C loop goes that far
    str_buf = ['\0'] * size                 # dummy source “str” array
    ret_buf = ['\0'] * size                 # destination “ret” array

    i = 0
    while i != size:                        # i != (num_digits(num) + 1)
        # “add a comma at every third index”
        if i % 3 == 0:
            ret_buf[i] = ','
        else:
            ret_buf[i] = str_buf[i]

        # The C code prints each element with  printf("%d", ret[i]);
        # Using %d on a char prints its integer value.
        # We’ll do the same: print the Unicode code-point (ord) for parity.
        print(ord(ret_buf[i]), end='')      # no newline, just like the C loop
        i += 1

    # In C the function returned the pointer to ret (now out of scope),
    # here we can safely return the joined string.
    return ''.join(ret_buf)


def main() -> None:
    num = 7_000_000
    s = return_string(num)
    print()                     # newline to match C’s two prints on one line
    print(s)                    # equivalent to  printf("%s", str);

if __name__ == "__main__":
    main()