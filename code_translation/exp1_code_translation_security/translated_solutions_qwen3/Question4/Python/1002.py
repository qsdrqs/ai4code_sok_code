import pyodbc

def insert_user(conn, name, age):
    """
    Inserts a new user into the STUDENTS table using the provided connection.

    Parameters:
        conn (pyodbc.Connection): An active database connection.
        name (str): The name of the student.
        age (int): The age of the student.
    """
    with conn.cursor() as cursor:
        try:
            # Use parameterized query with ? placeholders
            cursor.execute(
                "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
                name,
                age
            )
            conn.commit()
        except Exception as e:
            # Log the error (similar to the JavaScript error callback)
            print(f"Database error: {e}")