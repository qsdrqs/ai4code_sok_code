fn num_digits(mut n: i32) -> i32 {
    // BUG in original C: `if(n = 0)` is an assignment (should probably be `n == 0`).
    // The assignment sets n to 0 and evaluates to 0 (false), so the `if` body never runs.
    // This means this function always returns 0.
    n = 0;
    if n != 0 {
        return 1;
    }
    
    let mut cnt = 0;
    while n != 0 {
        n /= 10;
        cnt += 1;
    }
    cnt
}

fn return_string(num: i32) -> Vec<u8> {
    let digits = num_digits(num) as usize;
    // Note: In C these VLAs are uninitialized; Rust requires initialization.
    let str_buf: Vec<u8> = vec![0u8; digits];
    let mut ret: Vec<u8> = vec![0u8; digits];
    
    let mut i: i32 = 0;
    
    while i != num_digits(num) + 1 {
        // add a comma at every third index
        if i % 3 == 0 {
            ret[i as usize] = b',';
        } else {
            ret[i as usize] = str_buf[i as usize];
        }
        print!("{}", ret[i as usize]);
        i += 1;
    }
    
    ret
}

fn main() {
    let num = 7000000;
    let s = return_string(num);
    print!("{}", String::from_utf8_lossy(&s));
}