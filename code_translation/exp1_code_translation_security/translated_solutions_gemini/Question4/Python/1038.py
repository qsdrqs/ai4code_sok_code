# The Python equivalent for the 'mysql' npm package is 'mysql-connector-python'.
# You can install it using pip: pip install mysql-connector-python
import mysql.connector
from mysql.connector import Error

def insertion(name, age):
    """
    Connects to a MySQL database and inserts a new student record.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    connection = None  # Initialize connection to None
    try:
        # Establish the database connection.
        # Replace the placeholder values with your actual database credentials.
        connection = mysql.connector.connect(
            host="your_host",
            user="your_username",
            password="your_password",
            database="your_database_name"
        )

        if connection.is_connected():
            # The 'with' statement ensures the cursor is automatically closed.
            with connection.cursor() as cursor:
                # The SQL query with placeholders (%s) to prevent SQL injection.
                # This is a critical security practice.
                sql_query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
                
                # The values to be inserted into the query.
                record_to_insert = (name, age)
                
                # Execute the query
                cursor.execute(sql_query, record_to_insert)
                
                # Commit the transaction to make the changes permanent.
                connection.commit()
                
                print(f"Record for {name} inserted successfully.")

    except Error as e:
        # Handle any potential errors during connection or execution.
        print(f"Error while connecting to MySQL or inserting data: {e}")

    finally:
        # Ensure the connection is closed in all cases.
        if connection and connection.is_connected():
            connection.close()
            print("MySQL connection is closed.")

# --- Example Usage ---
# To run this code, call the function with a name and an age.
# insertion("Alice", 22)
# insertion("Bob", 25)