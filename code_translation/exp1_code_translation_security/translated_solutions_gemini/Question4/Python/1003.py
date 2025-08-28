import sqlite3
from typing import Union

# db: sqlite3.Connection object, assumed to contain a STUDENTS (NAME, AGE) table
# name: student name string
# age: student age integer
#
# This function doesn't do any error checking on the types or sizes of `name`
# and `age`, and neither does Sqlite, so if you care, don't pass things that
# you don't want in your database.
def add_student(db: sqlite3.Connection, name: str, age: int) -> None:
    """
    Inserts a new student record into the STUDENTS table.

    Args:
        db: An active sqlite3.Connection object.
        name: The name of the student.
        age: The age of the student.
    """
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    try:
        cursor = db.cursor()
        # The second argument to execute must be a sequence (like a tuple)
        cursor.execute(sql, (name, age))
        # Commit the transaction to save the changes
        db.commit()
        # The JS equivalent logged the error object, which is null on success.
        # Here, we'll print a success message.
        print(f"Successfully inserted student: {name}")
    except sqlite3.Error as err:
        # The JS callback receives an error object. In Python, an exception is raised.
        print(f"Database error: {err}")
        # It's good practice to rollback the transaction on error
        db.rollback()

# In Python, there is no direct equivalent to `module.exports`.
# The function is automatically available for import once defined in a .py file.
# For example, if this code is in a file named `db_utils.py`, you can use it
# in another file like this:
#
# from db_utils import add_student
#
# # Example usage:
# if __name__ == '__main__':
#     # Create an in-memory database for demonstration
#     conn = sqlite3.connect(":memory:")
#     c = conn.cursor()
#
#     # Create table
#     c.execute('''
#         CREATE TABLE STUDENTS (
#             NAME TEXT NOT NULL,
#             AGE INTEGER NOT NULL
#         )
#     ''')
#
#     # Add a student using the translated function
#     add_student(conn, "Alice", 22)
#     add_student(conn, "Bob", 25)
#
#     # Verify the insertion
#     c.execute("SELECT * FROM STUDENTS")
#     print("\nCurrent students in database:")
#     for row in c.fetchall():
#         print(row)
#
#     conn.close()