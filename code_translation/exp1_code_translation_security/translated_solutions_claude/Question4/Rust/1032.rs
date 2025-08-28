fn add_student(name: &str, age: i32) -> String {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    sql
}

// add_student("XXXXX", 0);