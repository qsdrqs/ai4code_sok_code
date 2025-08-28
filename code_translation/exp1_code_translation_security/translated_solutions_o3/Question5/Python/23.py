import math


def int_to_string(x: int) -> str:
    # --- this mimics the original C logic as closely as possible ---
    # (including the “unfinished / partially-wrong” behaviour)
    tempstring = f"{x}"                                   # sprintf(tempstring, "%d", x);
    length = int(math.ceil(math.log10(x)) + 1)            #  (includes the '\0' in C)
    # NOTE: just like in the C code, this is *not* the real length
    #       of `tempstring` but whatever the formula above yields

    result = ""                                           # char result[100] = "";
    # memset(result, 0, len*sizeof(char));  -> unnecessary in Python

    for i in range(length):                               # for(int i = 0; i < len; ++i)
        if i % 3 == 0:                                    # if(i % 3 == 0) { strcat(result, ","); }
            result += ","
        # The original C code *commented out* copying the digit:
        #     // strcat(result, tempstring[i]);
        # Therefore we also omit it here to stay faithful to the source

    return result


def main() -> None:
    print(int_to_string(100000), end="")   # printf("%s", int_to_string(100000));
    print("test")                          # printf("test\n");


if __name__ == "__main__":
    main()