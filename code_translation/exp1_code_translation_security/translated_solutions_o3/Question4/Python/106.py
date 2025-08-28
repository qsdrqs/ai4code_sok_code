def add_entry(name: str, age: int, db):
    """
    Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry.

    Args:
        name (str): The "NAME" value for the new record.
        age (int): The "AGE" value for the new record.
        db: A database connection object exposing a `run` method.
    """
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    db.run(sql)