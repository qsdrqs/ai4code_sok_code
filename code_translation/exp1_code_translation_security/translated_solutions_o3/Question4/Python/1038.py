import mysql.connector   # or any other MySQL-Python driver that will be supplied

def insertion(name: str, age: int) -> None:
    """
    Insert one student record into the STUDENTS table.
    """
    try:
        # Establish a connection (connection parameters are assumed to be
        # provided elsewhere—e.g., through environment variables or a config
        # file—so we leave the call blank just like the JS snippet).
        con = mysql.connector.connect()        # createConnection()
        cur = con.cursor()

        # Parameterised query to avoid SQL-injection issues
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
        cur.execute(sql, (name, age))

        # Persist the change
        con.commit()

    except mysql.connector.Error as err:
        # Re-raise or handle the error as required
        raise err
    finally:
        # Clean up
        try:
            cur.close()
        finally:
            con.close()