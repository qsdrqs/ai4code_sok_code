struct Students {
    // internal data structure
}

impl Students {
    fn add(&mut self, name: &str, age: i32) {
        // implementation
    }
}

static mut STUDENTS: Students = Students { /* initialization */ };

fn my_function(name: &str, age: i32) {
    unsafe {
        STUDENTS.add(name, age);
    }
}