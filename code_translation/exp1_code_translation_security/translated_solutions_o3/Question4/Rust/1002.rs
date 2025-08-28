use tiberius::{Client, Error};          // “tiberius” is a native SQL-Server/TDS driver
use futures::TryStreamExt;              // needed for `.into_results()`

// The connection (i.e. `client`) is assumed to be open and usable, just like in the
// original JavaScript example.
pub async fn insert_user<C>(
    client: &mut Client<C>,
    name:  &str,
    age:   i32,
) -> Result<(), Error>
where
    C: tiberius::ClientSession,         // any transport that Tiberius understands
{
    // In Tiberius the placeholders are positional (@P1, @P2 …).
    client
        .execute(
            "INSERT INTO STUDENTS (NAME, AGE) VALUES (@P1, @P2);",
            &[&name, &age],
        )
        .await?            // send the query
        .into_results()    // drain result‐sets so the connection stays usable
        .await?;           // propagate any SQL-Server error back to the caller

    Ok(())
}