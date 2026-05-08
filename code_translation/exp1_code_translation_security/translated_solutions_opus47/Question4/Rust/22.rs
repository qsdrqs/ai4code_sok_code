fn my_function(name: String, age: u32) {
       STUDENTS.lock().unwrap().add(name, age);
   }