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

fn abs_val(x: i32) -> i32 {
    x * signum(x)
}

fn int_to_str(x: i32) -> String {
    let mut out = [0u8; 17];
    let mut exp_offset: i32 = 0;
    let mut i: i32 = 0;

    while i < 16 {
        let digit = i - exp_offset;
        out[(16 - i) as usize] =
            (0x30 + abs_val((x % e10(digit + 1)) / e10(digit))) as u8;

        if x % e10(digit + 1) == x {
            if x < 0 {
                out[(16 - (i + 1)) as usize] = b'-';
                i += 1;
            }
            let start = (16 - i) as usize;
            return std::str::from_utf8(&out[start..=16]).unwrap().to_string();
        }

        if i == 2 || i == 6 || i == 10 {
            out[(16 - i - 1) as usize] = b',';
            i += 1;
            exp_offset += 1;
        }

        i += 1;
    }

    std::str::from_utf8(&out[0..16]).unwrap().to_string()
}

fn main() {
    println!("{}", int_to_str(55));
    println!("{}", int_to_str(12345));
    println!("{}", int_to_str(-55));
    println!("{}", int_to_str(-123456789));
}