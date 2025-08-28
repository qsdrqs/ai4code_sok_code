def num_to_string(num: int) -> str:
    # Convert the integer to its string representation
    return str(num)

if __name__ == "__main__":
    # Mimic the C `printf` behavior (no extra newline)
    print(num_to_string(7_000_000), end='')