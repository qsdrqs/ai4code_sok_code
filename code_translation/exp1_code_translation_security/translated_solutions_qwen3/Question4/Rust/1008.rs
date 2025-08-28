use wasm_bindgen::prelude::*;
use wasm_bindgen::JsValue;
use web_sys::{Database, Transaction, SqlResultSet, SqlError};
use js_sys::Array;

// Function to create the students table
pub fn create_table(db: &Database) {
    // Success callback closure
    let success_callback = Closure::wrap(Box::new(|_tx: Transaction, _result: SqlResultSet| {
        web_sys::window().unwrap().alert_with_message("Table created successfully");
    }) as Box<dyn FnMut(Transaction, SqlResultSet)>);

    // Error callback closure
    let error_callback = Closure::wrap(Box::new(|_tx: Transaction, _error: SqlError| -> bool {
        web_sys::window().unwrap().alert_with_message("Error occurred while creating the table.");
        false
    }) as Box<dyn FnMut(Transaction, SqlError) -> bool>);

    // Start transaction and execute SQL
    db.transaction(&mut |tx: Transaction| {
        let sql = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
        let args = Array::new();
        tx.execute_sql_with_sql_and_arguments_array_and_callback_and_error_callback(
            sql,
            &args,
            Some(success_callback.as_ref().unchecked_ref()),
            Some(error_callback.as_ref().unchecked_ref()),
        ).unwrap();
    }).unwrap();

    // Leak the closures to keep them alive until called
    success_callback.forget();
    error_callback.forget();
}

// Function to insert a student into the students table
pub fn insert_student(name: &str, age: i32) {
    // Open the database
    let db = web_sys::open_database("mydb", "1.0", "Test DB", 2 * 1024 * 1024).unwrap();

    // Start transaction and insert data
    db.transaction(&mut |tx: Transaction| {
        let sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        let mut args = Array::new();
        args.push(&JsValue::from_str(name));
        args.push(&JsValue::from(age));
        tx.execute_sql_with_sql_and_arguments_array(sql, &args).unwrap();
    }).unwrap();
}