# Any required modules (e.g. pyodbc / pymssql / …) will already be available.
# `conn` is an open SQL-Server connection object that exposes .cursor()/.commit().

def insert_user(conn, name: str, age: int) -> None:
    """
    Insert a (name, age) pair into the STUDENTS table.

    Parameters
    ----------
    conn : <connection>
        An active database connection.
    name : str
        The student's name.
    age : int
        The student's age.
    """
    try:
        with conn.cursor() as cur:
            # Parameter placeholders are “?” for pyodbc; change to “%s” if your
            # driver expects that style.
            cur.execute(
                "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);",
                (name, age)
            )
        conn.commit()        # Commit once the INSERT succeeds
    except Exception as exc:
        # Mimic console.log(err) from the JS version
        print(exc)