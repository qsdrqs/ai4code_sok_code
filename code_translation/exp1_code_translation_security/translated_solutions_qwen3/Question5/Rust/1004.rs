fn main() {
    let n = 0;
    let s = repr(n);
    println!("{}", s);
}

pub fn repr(n: i32) -> String {
    if n == i32::MIN {
        return "-2,147,483,648".to_string();
    }

    if n == 0 {
        return "0".to_string();
    }

    let sign = if n < 0 { -1 } else { 1 };
    let mut n_abs = if n < 0 { -n } else { n };

    let mut buffer = Vec::new();

    while n_abs > 0 {
        let digit = (n_abs % 10) as u8;
        buffer.push((b'0' + digit) as char);

        let current_i = buffer.len();

        if (current_i + 1) % 4 == 0 && n_abs > 10 {
            buffer.push(',');
        }

        n_abs /= 10;
    }

    if sign == -1 {
        buffer.push('-');
    }

    buffer.reverse();

    buffer.into_iter().collect()
}