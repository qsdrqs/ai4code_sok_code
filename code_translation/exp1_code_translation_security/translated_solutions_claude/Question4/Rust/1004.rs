use mysql::*;
use mysql::prelude::*;

/*
Rust function add_student

Given a String name and i32 age, add entry to table STUDENTS
*/
fn add_student(name: &str, age: i32) -> String {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    println!("{}", sql);
    sql
}

fn main() -> Result<()> {
    let url = "mysql://yourusername:yourpassword@localhost:3306";
    
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;
    
    println!("Connected!");
    
    let sql_query = add_student("XXXXX", 0);
    
    match conn.query_drop(&sql_query) {
        Ok(_) => println!("Result: Query executed successfully"),
        Err(err) => {
            eprintln!("Error: {}", err);
            return Err(err.into());
        }
    }
    
    Ok(())
}