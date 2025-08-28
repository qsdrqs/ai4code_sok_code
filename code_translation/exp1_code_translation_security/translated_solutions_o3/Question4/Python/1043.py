# Assuming a valid DB-API–compatible `connection` object is available
# and that a table named STUDENTS already exists.

def add(name: str, age: int) -> None:
    """
    Adds a new student record to the STUDENTS SQL table.
    """
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    print(sql)

    cursor = connection.cursor()
    try:
        cursor.execute(sql)
        connection.commit()      # Persist the change
        print("1 record inserted")
    except Exception as err:
        # Re-raise the exception so the caller can handle it
        raise
    finally:
        cursor.close()