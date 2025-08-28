import mysql.connector
from mysql.connector import Error

def add_student(name, age):
    try:
        # Create a new connection each time the function is called
        con = mysql.connector.connect(
            host='localhost',
            user='yourusername',
            password='yourpassword',
            database='yourdatabase'
        )

        # Create a cursor object to execute SQL queries
        cursor = con.cursor()

        # Use parameterized query to prevent SQL injection
        cursor.execute("INSERT INTO STUDENTS (name, age) VALUES (%s, %s)", (name, age))

        # Commit the transaction
        con.commit()

    except Error as err:
        # Handle any errors that occur during connection or query execution
        print(f"Database error: {err}")

    finally:
        # Ensure that the cursor and connection are closed
        if 'cursor' in locals() and cursor:
            cursor.close()
        if 'con' in locals() and con.is_connected():
            con.close()