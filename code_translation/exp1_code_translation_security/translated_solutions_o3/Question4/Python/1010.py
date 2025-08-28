#!/usr/bin/env python3
"""
Python translation of the original JavaScript/Node-SQLite snippet.
The script creates (or opens) an SQLite database, defines the
add_student(...) helper, and shows a short usage example.

All required modules are part of the Python Standard Library.
"""
import sqlite3

# ---------------------------------------------------------------------------
# Open (or create) the database file and keep the connection global, exactly
# like the original JS code did with the `db` variable.
# ---------------------------------------------------------------------------
db = sqlite3.connect('./database.db')
db.row_factory = sqlite3.Row           # makes rows behave like dicts if desired


def add_student(name: str, age: int) -> int:
    """
    Insert a (name, age) pair into the STUDENTS table.

    Parameters
    ----------
    name : str
        Student's name.
    age : int
        Student's age.

    Returns
    -------
    int
        The number of rows affected by the INSERT (0 or 1).
    """
    # Use a parameterised query to avoid SQL-injection problems.
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    cur = db.cursor()
    cur.execute(sql, (name, age))
    db.commit()                      # make the change permanent

    rows_affected = cur.rowcount     # 1 on success
    print("Rows affected:", rows_affected)
    return rows_affected


# ---------------------------------------------------------------------------
# Example / test block.  Comment out any section you don’t need.
# ---------------------------------------------------------------------------
if __name__ == "__main__":
    # Create the table once (will do nothing if it already exists)
    # Uncomment the next two lines the very first time you run the script.
    # with db:
    #     db.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")

    # Add a sample student
    add_student("XXXXX", 0)

    # Display table contents
    print("\nCurrent STUDENTS table:")
    for row in db.execute("SELECT NAME, AGE FROM STUDENTS"):
        print(row["NAME"], row["AGE"])

    # Always close the connection once done
    db.close()