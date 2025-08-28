import mysql.connector
from mysql.connector import Error

def create_entry(name, age):
    try:
        # Establish a new database connection
        con = mysql.connector.connect(
            host='localhost',
            user='your_username',
            password='your_password',
            database='your_database'
        )

        # Create a cursor object to interact with the database
        cursor = con.cursor()

        # Execute the INSERT query with parameterized values
        cursor.execute("INSERT INTO STUDENTS (name, age) VALUES (%s, %s)", (name, age))

        # Commit the transaction
        con.commit()

    except Error as e:
        # Re-raise the error to propagate it
        raise e

    finally:
        # Ensure that the cursor and connection are closed
        if 'cursor' in locals() and cursor:
            cursor.close()
        if 'con' in locals() and con.is_connected():
            con.close()