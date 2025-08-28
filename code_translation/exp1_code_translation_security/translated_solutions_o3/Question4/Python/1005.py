# Equivalent Python implementation

import mysql.connector  # Dependency will be available

# Create a reusable connection-factory (fill in your real credentials)
def _get_connection():
    return mysql.connector.connect(
        host='YOUR_HOST',
        user='YOUR_USER',
        password='YOUR_PASSWORD',
        database='YOUR_DATABASE'
    )

def add_student(name: str, age: int) -> None:
    """
    Insert a student row into the STUDENTS table.

    Parameters
    ----------
    name : str
        Student’s name
    age : int
        Student’s age
    """
    # Establish a connection (mirrors `con.connect` in the JS code)
    con = _get_connection()
    cur = con.cursor()

    try:
        # Use parameterized query to avoid SQL-injection issues
        cur.execute("INSERT INTO STUDENTS VALUES (%s, %s)", (name, age))
        con.commit()
    finally:
        # Clean up cursor and connection no matter what happens
        cur.close()
        con.close()