# NOTE:
# 1) The surrounding infrastructure (DB driver, connection string, etc.) is assumed to
#    be supplied elsewhere in your project, as per the instructions.
# 2) The only thing this function does is insert a new row in the STUDENTS table
#    with the given name and age, then prints the DB-driver’s response (or any
#    error that bubbles up).
# 3) The example below uses the standard library’s `sqlite3` module because it
#    ships with Python; swap it out for psycopg2, MySQL-connector, SQLAlchemy,
#    etc. as needed.

import sqlite3

def insert_student(name: str, age: int) -> None:
    """
    Insert a single (name, age) record into the STUDENTS table.

    Parameters
    ----------
    name : str
        The student's name.
    age : int
        The student's age.

    Returns
    -------
    None
    """
    # Establish connection (replace with your own DSN / connection pool)
    conn = sqlite3.connect("students.db")   # e.g. 'dbname=foo user=bar' for Postgres
    try:
        with conn:  # automatically commits (or rolls back on exception)
            conn.execute(
                "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);",
                (name, age)
            )
        print("Row successfully inserted.")
    finally:
        conn.close()

# Example usage (mirrors the original JavaScript call)
insert_student("XXXXX", 0)