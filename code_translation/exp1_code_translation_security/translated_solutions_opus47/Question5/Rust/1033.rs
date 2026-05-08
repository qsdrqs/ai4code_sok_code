fn num_to_str(num: i32, s: &mut [u8]) {
    // Equivalent of sprintf(str, "%d", num)
    let formatted = format!("{}", num);
    let bytes = formatted.as_bytes();
    let length = bytes.len();
    
    s[..length].copy_from_slice(bytes);
    s[length] = 0;  // null terminator
    
    if num == 0 {
        return;
    } else if num > 0 {
        let _num_commas = (length - 1) / 3;
        let mut digits_moved = 0;
        
        for i in (1..=length).rev() {
            if digits_moved < 3 {
                s[i] = s[i - 1];
                digits_moved += 1;
            } else {
                s[i] = b',';
                digits_moved = 0;
            }
        }
    } else {
        let _num_commas = (length - 2) / 3;
        let mut digits_moved = 0;
        
        for i in (2..=length).rev() {
            if digits_moved < 3 {
                s[i] = s[i - 1];
                digits_moved += 1;
            } else {
                s[i] = b',';
                digits_moved = 0;
            }
        }
    }
}

fn main() {
    //let str = num_to_str(7000);
    let num = 7042;
    let mut s = [0u8; 24];
    
    num_to_str(num, &mut s);
    
    // Find null terminator and convert to string (like C's %s)
    let end = s.iter().position(|&c| c == 0).unwrap_or(s.len());
    let result = std::str::from_utf8(&s[..end]).unwrap();
    
    print!("Output: {}", result);
    
    //print!("Hi");
}