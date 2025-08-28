fn e10(x: i32) -> i32 {
    let mut v = 1;
    for _ in 0..x {
        v *= 10;
    }
    v
}

fn signum(x: i32) -> i32 {
    match x {
        x if x < 0 => -1,
        0 => 0,
        _ => 1,
    }
}

fn abs(x: i32) -> i32 {
    x * signum(x)
}

fn int_to_str(x: i32) -> String {
    let mut out = String::new();
    let mut is_negative = false;

    if x < 0 {
        is_negative = true;
        let abs_x = abs(x);
        out.push_str(&format!("{}", abs_x));
    } else {
        out.push_str(&format!("{}", x));
    }

    let mut formatted_out = String::new();
    let mut count = 0;
    for c in out.chars().rev() {
        formatted_out.push(c);
        count += 1;
        if count == 3 && out.len() > 3 {
            formatted_out.push(',');
            count = 0;
        }
    }

    let mut final_out = String::new();
    for c in formatted_out.chars().rev() {
        final_out.push(c);
    }

    if is_negative {
        final_out.insert(0, '-');
    }

    final_out
}

fn main() {
    println!("{}", int_to_str(55));
    println!("{}", int_to_str(12345));
    println!("{}", int_to_str(-55));
    println!("{}", int_to_str(-123456789));
}