fn e10(x: i32) -> i32 {
    let mut v = 1;
    for _ in 0..x {
        v *= 10;
    }
    v
}

fn signum(x: i32) -> i32 {
    if x < 0 {
        -1
    } else if x == 0 {
        0
    } else {
        1
    }
}

fn abs(x: i32) -> i32 {
    x * signum(x)
}

fn int_to_str(x: i32) -> String {
    let mut out = ['\0'; 16];
    let mut exp_offset = 0;

    for i in 0..16 {
        let digit = i - exp_offset;
        out[15 - i] = (0x30 + abs((x % e10(digit + 1)) / e10(digit))) as u8 as char;

        if x % e10(digit + 1) == x {
            if x < 0 {
                out[15 - (i + 1)] = '-';
                return out[15 - (i + 1)..16].iter().collect();
            }
            return out[15 - i..16].iter().collect();
        }

        if i == 2 || i == 6 || i == 10 {
            out[15 - i - 1] = ',';
            exp_offset += 1;
        }
    }
    out.iter().collect()
}

fn main() {
    println!("{}", int_to_str(55));
    println!("{}", int_to_str(12345));
    println!("{}", int_to_str(-55));
    println!("{}", int_to_str(-123456789));
}