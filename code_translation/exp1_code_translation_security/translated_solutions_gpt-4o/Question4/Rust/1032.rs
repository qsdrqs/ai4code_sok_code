fn add_student(name: &str, age: u32) -> String {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    sql
}

// Example usage:
// let query = add_student("XXXXX", 0);
// println!("{}", query);