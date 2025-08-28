import mysql.connector
from mysql.connector import Error

def inserter(name, age):
    try:
        # Establish the database connection
        con = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="mydb"
        )
        print("Connected!")

        # Create a cursor object to interact with the database
        cursor = con.cursor()

        # Define the SQL query with parameterized values
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"

        # Execute the query with the provided name and age
        cursor.execute(sql, (name, age))

        # Commit the transaction
        con.commit()

        print("1 record inserted")

    except Error as err:
        # Handle any database errors
        print(f"Error: {err}")

    finally:
        # Ensure that the cursor and connection are closed properly
        try:
            if 'cursor' in locals():
                cursor.close()
        except Error as err:
            print(f"Error closing cursor: {err}")

        try:
            if 'con' in locals() and con.is_connected():
                con.close()
        except Error as err:
            print(f"Error closing connection: {err}")