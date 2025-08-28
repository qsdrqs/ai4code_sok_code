import sqlite3  # This will be provided, assuming SQLite database

def add_student(name: str, age: int) -> str:
    """
    Generates an SQL query string to add a student to the database.

    Args:
        name (str): The student's name.
        age (int): The student's age.

    Returns:
        str: The SQL query string.
    """
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    return sql

# Example usage:
if __name__ == "__main__":
    # Assuming a SQLite database connection
    # conn = sqlite3.connect('students.db')
    # cursor = conn.cursor()
    
    # Generate SQL query
    query = add_student("XXXXX", 0)
    print(query)