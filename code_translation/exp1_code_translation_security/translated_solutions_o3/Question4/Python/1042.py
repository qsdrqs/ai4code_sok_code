def add_student(name, age):
    # Build the SQL statement (parameterized to avoid SQL-injection issues)
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"

    # Obtain a cursor from the (already available) connection object
    cursor = connection.cursor()

    try:
        # Execute the query with the provided parameters
        cursor.execute(sql, (name, age))
        connection.commit()          # Persist the change

        print("1 record inserted")
    except Exception as err:
        connection.rollback()        # Undo on error
        raise err                    # Re-raise so callers can handle it
    finally:
        cursor.close()