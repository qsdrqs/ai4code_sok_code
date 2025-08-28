import sqlite3

def add_entry(name: str, age: int, db: sqlite3.Connection):
    """
    Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

    Args:
        name (str): string for the "NAME" of the entry
        age (int): int for the "AGE" of the entry
        db (sqlite3.Connection): a given database connection
    """
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    db.execute(sql, (name, age))
    db.commit()

# Example usage:
if __name__ == "__main__":
    # Establish a connection to the database
    conn = sqlite3.connect('example.db')
    cursor = conn.cursor()

    # Create table if it doesn't exist
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS STUDENTS
        (NAME TEXT, AGE INTEGER)
    ''')

    # Add a new entry
    add_entry('John Doe', 20, conn)

    # Close the connection
    conn.close()