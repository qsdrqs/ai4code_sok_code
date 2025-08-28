import pyodbc # The Python equivalent for connecting to SQL Server, similar to 'tedious'

# assume active conn
def insert_user(conn, name, age):
    """
    Inserts a new user into the STUDENTS table using an active pyodbc connection.

    Args:
        conn: An active pyodbc connection object.
        name (str): The name of the student.
        age (int): The age of the student.
    """
    # In pyodbc, we use '?' as parameter markers instead of '@NAME'
    sql_insert_query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);"
    
    # The 'tedious' callback function is best translated as a try/except block in Python
    try:
        # A cursor is needed to execute commands
        cursor = conn.cursor()
        
        # Execute the query, passing parameters as a tuple
        cursor.execute(sql_insert_query, name, age)
        
        # Commit the transaction to make the changes permanent
        conn.commit()
        
    except pyodbc.Error as err:
        print(f"An error occurred: {err}")
        # It's good practice to roll back the transaction on error
        conn.rollback()
    finally:
        # Clean up the cursor
        cursor.close()