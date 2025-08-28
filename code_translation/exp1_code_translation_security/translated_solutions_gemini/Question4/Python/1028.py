# First, you need to install the library:
# pip install mysql-connector-python

import mysql.connector
from mysql.connector import Error

def add_student(connection, name, age):
    """
    Inserts a new student record into the STUDENTS table.

    Args:
        connection: The database connection object.
        name (str): The name of the student.
        age (int): The age of the student.
    """
    # 1. Use placeholders (%s) to prevent SQL injection attacks.
    #    This is the most important improvement over the original JavaScript code.
    sql_query = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"
    
    # 2. The values are passed as a separate tuple.
    #    The database driver handles safely quoting and escaping the values.
    values = (name, age)
    
    cursor = None  # Initialize cursor to None
    try:
        # 3. A cursor is used to execute queries in Python's DB-API.
        cursor = connection.cursor()
        cursor.execute(sql_query, values)
        
        # 4. Commit the transaction to make the changes permanent.
        connection.commit()
        
        print(f"{cursor.rowcount} record inserted.")
        
    except Error as err:
        # 5. Proper error handling using a try...except block.
        print(f"Error: {err}")
        # Optionally, you could re-raise the exception:
        # raise err
        
    finally:
        # 6. It's good practice to close the cursor.
        if cursor:
            cursor.close()

# --- Example of how to use the function ---
if __name__ == "__main__":
    db_connection = None  # Initialize connection to None
    try:
        # Establish a database connection
        # (Replace with your actual database credentials)
        db_connection = mysql.connector.connect(
            host="localhost",
            user="your_username",
            password="your_password",
            database="your_database"
        )
        
        if db_connection.is_connected():
            print("Connected to MySQL database")
            
            # Call the function to add a new student
            add_student(db_connection, "Alice", 22)
            add_student(db_connection, "Bob", 25)

    except Error as e:
        print(f"Error while connecting to MySQL: {e}")
        
    finally:
        # Ensure the connection is closed
        if db_connection and db_connection.is_connected():
            db_connection.close()
            print("MySQL connection is closed")