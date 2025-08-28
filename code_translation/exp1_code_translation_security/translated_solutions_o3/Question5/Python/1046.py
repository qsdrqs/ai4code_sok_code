# No extra imports are strictly necessary, but we’ll use sys.stdout.write
# to mimic printf behaviour (i.e. “print without the automatic newline”).
import sys


def convert(num: int) -> str:
    """
    Emulates the C function:
        - Builds a string representation of `num`
        - Prints that string immediately (no newline)
        - Returns the same string
    """
    buf = f"{num}"          # same as sprintf(buf, "%d", num)
    sys.stdout.write(buf)   # same as printf("%s", buf) in C
    return buf


def main() -> None:
    # Equivalent to the C `main` body
    # convert(100) prints "100" and returns the string "100"
    buf = convert(100)
    # Now print the first character of that string (same as printf("%c", …))
    sys.stdout.write(buf[0])


if __name__ == "__main__":
    main()