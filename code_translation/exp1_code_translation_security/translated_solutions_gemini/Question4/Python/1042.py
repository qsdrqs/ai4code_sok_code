# First, you need to install the database connector if you haven't already
# pip install mysql-connector-python

import mysql.connector
from mysql.connector import Error

# --- Database Connection Setup ---
# It's recommended to establish the connection outside the function
# and pass it in, or handle it within a context manager as shown below.
# Replace with your actual database credentials.
db_config = {
    'host': 'localhost',
    'user': 'your_username',
    'password': 'your_password',
    'database': 'your_database'
}

def add_student(name, age):
    """
    Inserts a new student record into the STUDENTS table.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    # The SQL query uses %s as placeholders for data.
    # This is a security best practice to prevent SQL injection.
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    
    connection = None  # Initialize connection to None
    try:
        # The 'with' statement ensures resources are automatically closed
        connection = mysql.connector.connect(**db_config)
        with connection.cursor() as cursor:
            # Pass the values as a tuple to the execute method
            cursor.execute(sql, (name, age))
            
            # Commit the transaction to make the changes permanent
            connection.commit()
            
            print(f"{cursor.rowcount} record inserted.")

    except Error as err:
        print(f"Error: {err}")
        # Optionally, re-raise the exception if the caller should handle it
        # raise err

    finally:
        # Ensure the connection is closed even if an error occurs
        if connection and connection.is_connected():
            connection.close()

# --- Example Usage ---
if __name__ == "__main__":
    # Add a new student named 'Alice' who is 22 years old
    add_student('Alice', 22)
    
    # Add another student
    add_student('Bob', 25)