// All necessary dependencies are brought into scope.
// wasm_bindgen is the glue between Rust/Wasm and JavaScript.
use wasm_bindgen::prelude::*;
use wasm_bindgen::JsCast;
// web_sys is a crate with raw bindings to all the Web's APIs.
use web_sys::{window, Document, HtmlElement, HtmlTableElement};

// This attribute makes the Rust function visible and callable from JavaScript.
#[wasm_bindgen]
// Rust function that takes as input a string slice `name` and an integer `age`.
// It's best practice to return a Result to handle potential errors, like an element not being found.
// JsValue is a type that can represent any JavaScript value, often used for errors.
pub fn add_student(name: &str, age: i32) -> Result<(), JsValue> {
    // Use `web_sys` to get the global `window` object from the browser.
    // .ok_or_else(...) converts the Option into a Result, providing a clear error message if `window` is None.
    let window = window().ok_or_else(|| JsValue::from_str("no global `window` exists"))?;
    
    // Get the `document` object from the `window`.
    let document = window.document().ok_or_else(|| JsValue::from_str("should have a document on window"))?;

    // Get the table element by its ID. This returns a generic `Element`.
    let table_element = document
        .get_element_by_id("students")
        .ok_or_else(|| JsValue::from_str("Could not find element with id 'students'"))?;

    // We need to cast the generic `Element` into a more specific `HtmlTableElement`
    // to access table-specific methods like `insert_row_with_index`.
    // .dyn_into() performs this dynamic cast and returns a Result.
    let table = table_element
        .dyn_into::<HtmlTableElement>()
        .map_err(|_| JsValue::from_str("Element with id 'students' is not a table"))?;

    // Insert a new row (`<tr>`) into the table at index 1 (the second row).
    // This corresponds to JavaScript's `table.insertRow(1)`.
    // The method returns a generic `HtmlElement` representing the new row.
    let row = table.insert_row_with_index(1)?
                   .dyn_into::<web_sys::HtmlTableRowElement>()?; // Cast to a row element to use its methods

    // Insert a new cell (`<td>`) into the row at index 0.
    let cell1 = row.insert_cell_with_index(0)?;
    
    // Insert another new cell (`<td>`) at index 1.
    let cell2 = row.insert_cell_with_index(1)?;

    // Set the inner HTML of the first cell to the student's name.
    cell1.set_inner_html(name);
    
    // Set the inner HTML of the second cell to the student's age.
    // The integer `age` must be converted to a string first.
    cell2.set_inner_html(&age.to_string());

    // Return `Ok(())` to indicate that the function executed successfully.
    // `()` is the unit type, similar to `void` in other languages.
    Ok(())
}