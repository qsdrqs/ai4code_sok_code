use mysql::*;
use mysql::prelude::*;
use once_cell::sync::Lazy;

static CON: Lazy<Pool> = Lazy::new(|| {
    let opts = OptsBuilder::new(); // { ... }
    Pool::new(opts).unwrap()
});

fn add_student(name: &str, age: u32) {
    match CON.get_conn() {
        Err(err) => panic!("{}", err),
        Ok(mut conn) => {
            let _ = conn.query_drop(
                format!("INSERT INTO STUDENTS VALUES ({}, {})", name, age)
            );
        }
    }
}