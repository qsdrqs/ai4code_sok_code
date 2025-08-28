fn reverse(s: &mut Vec<char>) {
    let mut i = 0;
    let mut j = s.len() - 1;
    
    while i < j {
        let c = s[i];
        s[i] = s[j];
        s[j] = c;
        i += 1;
        j -= 1;
    }
}

fn itoa(mut n: i32, s: &mut Vec<char>) {
    let sign = n < 0;
    if sign {
        n = -n; // make n positive
    }
    
    s.clear();
    
    // generate digits in reverse order
    loop {
        s.push((n % 10) as u8 as char + '0'); // get next digit
        n /= 10;
        if n == 0 {
            break;
        }
    }
    
    if sign {
        s.push('-');
    }
}

fn insert_comma(s: &mut Vec<char>, pos: usize) {
    s.insert(pos, ',');
}

fn int_to_string(num: i32) -> String {
    let mut s = Vec::new();
    itoa(num, &mut s);
    
    let length = s.len();
    let mut i = 3;
    while i <= length {
        insert_comma(&mut s, i);
        i += 4;
    }
    
    // Remove trailing comma if it exists
    if let Some(&',') = s.last() {
        s.pop();
    }
    
    reverse(&mut s);
    
    s.into_iter().collect()
}

fn main() {
    let num = 7000;
    let result = int_to_string(num);
    println!("{}", result);
}