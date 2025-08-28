# First, you need to install the mysql-connector-python library:
# pip install mysql-connector-python

import mysql.connector
from mysql.connector import Error

def insert(name: str, age: int):
    """
    Connects to a MySQL database and inserts a new record into the 'students' table.

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
            database="STUDENTS"
        )

        if connection.is_connected():
            print("Connected to MySQL database")
            
            # A cursor is used to execute statements to communicate with the MySQL database
            cursor = connection.cursor()
            
            # The SQL query with placeholders (%s) to prevent SQL injection
            sql_query = "INSERT INTO students (name, age) VALUES (%s, %s)"
            
            # The values to be inserted into the table
            record_to_insert = (name, age)
            
            # Execute the query
            cursor.execute(sql_query, record_to_insert)
            
            # Commit the transaction to make the changes permanent
            connection.commit()
            
            print(f"{cursor.rowcount} record inserted successfully.")
            
            # Close the cursor
            cursor.close()

    except Error as e:
        print(f"Error while connecting to MySQL or inserting data: {e}")

    finally:
        # Ensure the connection is closed
        if connection and connection.is_connected():
            connection.close()
            print("MySQL connection is closed")

# --- Example Usage ---
if __name__ == "__main__":
    # Call the function with sample data
    insert("Alice", 22)
    insert("Bob", 25)