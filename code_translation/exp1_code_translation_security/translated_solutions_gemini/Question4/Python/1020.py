import mysql.connector
from mysql.connector import Error

def inserter(name, age):
    """
    Connects to a MySQL database and inserts a new record into the STUDENTS table.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    connection = None  # Initialize connection to None
    try:
        # Establish the database connection
        connection = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="mydb"
        )

        if connection.is_connected():
            print("Connected!")
            cursor = connection.cursor()

            # The SQL query with placeholders (%s) to prevent SQL injection
            sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
            
            # The values to be inserted into the placeholders
            values = (name, age)
            
            # Execute the query
            cursor.execute(sql, values)
            
            # Commit the transaction to make the changes permanent
            connection.commit()
            
            print(f"{cursor.rowcount} record inserted.")
            cursor.close()

    except Error as e:
        # Handle potential errors
        print(f"Error: {e}")

    finally:
        # Ensure the connection is closed, whether an error occurred or not
        if connection and connection.is_connected():
            connection.close()
            print("MySQL connection is closed")

# Example of how to call the function:
# inserter("Alice", 22)
# inserter("Bob", 25)