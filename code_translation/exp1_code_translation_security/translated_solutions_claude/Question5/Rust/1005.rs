const MAX_LEN: usize = 14;

fn stringify(i: i32, buf: &mut [char]) -> usize {
    let mut tmp = ['0'; MAX_LEN];
    let mut j = if i >= 0 { i } else { -i };
    let mut k = MAX_LEN - 1;
    let mut c = 0;
    
    while j > 0 && k > 0 {
        if c != 0 && c % 3 == 0 {
            tmp[k] = ',';
            if k > 0 {
                k -= 1;
            }
            c = 0;
        }
        
        // Convert digit to char
        let digit = j % 10;
        let d_char = char::from_digit(digit as u32, 10).unwrap();
        
        // Add to string
        tmp[k] = d_char;
        
        // Move to next digit
        j /= 10;
        if k > 0 {
            k -= 1;
        }
        c += 1;
    }
    
    if i < 0 && k > 0 {
        tmp[k] = '-';
        k -= 1;
    }
    
    // Copy from tmp to buf
    let mut buf_idx = 0;
    for n in (k + 1)..MAX_LEN {
        if buf_idx < buf.len() {
            buf[buf_idx] = tmp[n];
            buf_idx += 1;
        }
    }
    
    MAX_LEN - 1 - k
}

fn main() {
    let mut buf = ['0'; MAX_LEN];
    let input = -2147483647;
    let l = stringify(input, &mut buf);
    
    // Convert char array to string for printing
    let result: String = buf.iter().take(l).collect();
    println!("{} :: {}", l, result);
}