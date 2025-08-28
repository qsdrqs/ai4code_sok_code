def add_entry(name, age, db):
    """
    Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

    Args:
        name: string for the "NAME" of the entry
        age: int for the "AGE" of the entry
        db: a given database connection
    """
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    db.execute(sql, (name, age))