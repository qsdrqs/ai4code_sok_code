fn add_student(name: &str, age: i32) -> String {
    format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age)
}

// Example usage:
// let sql = add_student("XXXXX", 0);
// println!("{}", sql);