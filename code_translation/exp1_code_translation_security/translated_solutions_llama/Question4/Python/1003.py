import sqlite3
import logging

# Configure logging
logging.basicConfig(level=logging.INFO)

def add_student(db: sqlite3.Connection, name: str, age: int) -> None:
    """
    Adds a student to the STUDENTS table in the provided SQLite database.

    Args:
    - db (sqlite3.Connection): A SQLite database connection object.
    - name (str): The name of the student.
    - age (int): The age of the student.

    Note:
    This function does not perform error checking on the types or sizes of `name` and `age`.
    It is the caller's responsibility to ensure that the provided values are valid.
    """

    cursor = db.cursor()
    query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    try:
        cursor.execute(query, (name, age))
        db.commit()
        logging.info("Inserted student successfully.")
    except sqlite3.Error as e:
        db.rollback()
        logging.error(f"Error inserting student: {e}")

# Example usage
if __name__ == "__main__":
    # Establish a connection to the SQLite database
    conn = sqlite3.connect('students.db')
    
    # Create the STUDENTS table if it does not exist
    conn.execute('''
        CREATE TABLE IF NOT EXISTS STUDENTS
        (NAME TEXT, AGE INTEGER)
    ''')

    # Add a student
    add_student(conn, 'John Doe', 20)

    # Close the database connection
    conn.close()