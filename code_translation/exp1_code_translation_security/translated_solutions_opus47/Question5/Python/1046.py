def convert(num):
    buf = ""
    buf = str(num)          # equivalent to sprintf(buf, "%d", num)
    print(buf, end="")      # equivalent to printf("%s", buf)
    return buf

# main
print(convert(100), end="")  # equivalent to printf("%c", convert(100))