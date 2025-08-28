import sqlite3

# Function to add a new student to the database
def add_student(name: str, age: int, db: sqlite3.Connection) -> int:
    """
    Adds a new student to the 'STUDENTS' table in the database.

    Args:
    - name (str): The name of the student.
    - age (int): The age of the student.
    - db (sqlite3.Connection): The connection to the SQLite database.

    Returns:
    - int: The number of rows affected by the INSERT operation.
    """
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    cursor = db.cursor()
    cursor.execute(sql, (name, age))
    db.commit()
    return cursor.rowcount

# Test example
if __name__ == "__main__":
    # Create a connection to the SQLite database
    db = sqlite3.connect('./database.db')

    # Create table (only do this once!)
    # cursor = db.cursor()
    # sql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)"
    # cursor.execute(sql)
    # db.commit()

    # Add a new student
    name = "XXXXX"
    age = 0
    rows_affected = add_student(name, age, db)
    print(f"Rows affected: {rows_affected}")

    # Display contents of database
    cursor = db.cursor()
    cursor.execute("SELECT * FROM STUDENTS")
    rows = cursor.fetchall()
    for row in rows:
        print(row[0], row[1])

    # Close database
    db.close()