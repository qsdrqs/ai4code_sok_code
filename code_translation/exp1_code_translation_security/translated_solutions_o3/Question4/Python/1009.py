def create_entry(name, age):
    """
    Insert a row into the STUDENTS table.

    NOTE: A working connection object called `con`
          (e.g. a `mysql.connector` or `pymysql` connection)
          is presumed to be available in the scope.
    """
    try:
        # Open the connection (only needed if it is not open yet).
        con.connect()

        # Use a cursor to execute the query.
        with con.cursor() as cur:
            cur.execute(
                "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)",
                (name, age)
            )

        # Persist the change.
        con.commit()

    except Exception as err:
        # Re-raise the error—mirrors `throw err` in the JS code.
        raise err