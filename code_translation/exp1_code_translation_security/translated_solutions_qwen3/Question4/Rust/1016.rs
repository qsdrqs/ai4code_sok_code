use web_sys::XmlHttpRequest;

pub fn add_student(name: &str, age: i32) {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    let request = XmlHttpRequest::new().unwrap();
    request.open_with_async("POST", "http://localhost:8080/sql", true).unwrap();
    request.set_request_header("Content-type", "application/x-www-form-urlencoded").unwrap();
    request.send_with_opt_str(Some(&sql)).unwrap();
}