pub fn intotostr(n: i32) -> String {
    let mut buffer = [0u8; 11]; // 10 digits + null terminator
    let len = itoa::write(&mut buffer[..], n).unwrap();
    String::from_utf8_lossy(&buffer[..len]).into_owned()
}