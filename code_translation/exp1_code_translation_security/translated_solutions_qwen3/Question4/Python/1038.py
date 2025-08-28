def insertion(name, age):
    import mysql.connector

    # Establish a connection to the MySQL database
    con = mysql.connector.connect(
        host='localhost',
        user='root',
        password='',
        database='your_database'
    )

    try:
        # Create a cursor object to interact with the database
        cursor = con.cursor()

        # Define the SQL INSERT statement with parameterized values
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"

        # Execute the SQL statement with the provided values
        cursor.execute(sql, (name, age))

        # Commit the transaction to the database
        con.commit()

    except Exception as err:
        # If an error occurs, raise it (mirroring the JS behavior)
        raise err

    finally:
        # Ensure the connection and cursor are closed properly
        if 'cursor' in locals():
            cursor.close()
        if 'con' in locals():
            con.close()