def add_entry(name: str, age: int, db):
    """
    Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry.

    Args:
        name: string for the "NAME" of the entry.
        age: int for the "AGE" of the entry.
        db: a given database connection object (e.g., from sqlite3, psycopg2).
    """
    # The original JavaScript code was vulnerable to SQL injection.
    # The Pythonic and secure way is to use a parameterized query,
    # where '?' (or '%s' for some drivers) is a placeholder.
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    
    # We use a cursor to execute the command
    cursor = db.cursor()
    
    # The database driver safely substitutes the placeholders with the data
    cursor.execute(sql, (name, age))
    
    # Commit the transaction to make the change permanent
    db.commit()