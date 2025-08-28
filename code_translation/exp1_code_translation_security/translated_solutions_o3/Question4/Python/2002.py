# assumes a previously-created MySQL DB-API connection object named `connection`

def add_student(name, age):
    """
    Inserts a student record into the STUDENTS table.
    """
    query  = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    values = (name, age)

    with connection.cursor() as cursor:
        cursor.execute(query, values)

    # Persist the change if autocommit is not enabled
    connection.commit()