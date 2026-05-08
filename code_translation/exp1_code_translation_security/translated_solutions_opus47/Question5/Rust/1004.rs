/*
Function repr

Takes in a signed integer and returns the string representation of that integer.
*/

fn repr(n: i32) -> String {
    if n == i32::MIN {
        // When multiplied by -1 this overflows, so handle it separately.
        return String::from("-2,147,483,648");
    }
    if n == 0 {
        return String::from("0");
    }

    let mut result: Vec<u8> = Vec::new();
    let mut sign = 1;
    let mut n = n;
    let mut i = 0;

    if n < 0 {
        sign = -1;
        n = -n;
    }

    while n > 0 {
        result.push(((n % 10) as u8) + b'0');
        i += 1;
        if (i + 1) % 4 == 0 && n > 10 {
            result.push(b',');
            i += 1;
        }
        n /= 10;
    }

    if sign == -1 {
        result.push(b'-');
    }

    reverse(&mut result);
    String::from_utf8(result).unwrap()
}

/*
Function reverse

Takes in a string and reverses it.
*/

fn reverse(bytes: &mut Vec<u8>) {
    if bytes.is_empty() {
        return;
    }
    let mut i = 0;
    let mut j = bytes.len() - 1;
    while i < j {
        bytes.swap(i, j);
        i += 1;
        j -= 1;
    }
}

fn main() {
    let n = 0;
    let s = repr(n);
    print!("{}", s);
}