fn num_digits(mut n: i32) -> usize {
    if n == 0 {
        return 1;
    }
    
    if n < 0 {
        n = -n;
    }
    
    let mut cnt = 0;
    while n != 0 {
        n = n / 10;
        cnt += 1;
    }
    
    cnt
}

fn return_string(num: i32) -> String {
    let digit_count = num_digits(num);
    let num_str = num.to_string();
    let str_chars: Vec<char> = num_str.chars().collect();
    
    let mut ret = Vec::new();
    let mut i = 0;
    
    while i < digit_count {
        // Add a comma at every third index (this mirrors the original logic)
        if i % 3 == 0 && i > 0 {
            ret.push(',');
        }
        
        if i < str_chars.len() {
            ret.push(str_chars[i]);
            print!("{}", str_chars[i]);
        }
        i += 1;
    }
    
    ret.into_iter().collect()
}

fn main() {
    let num = 7000000;
    let str_result = return_string(num);
    println!("{}", str_result);
}