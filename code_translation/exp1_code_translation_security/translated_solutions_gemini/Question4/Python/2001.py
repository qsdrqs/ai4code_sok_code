# 1. Import the necessary library
import mysql.connector
from mysql.connector import errorcode

# --- Dependency Setup (This would be in your app's setup) ---
try:
    # Establish the database connection
    # Replace with your own database credentials
    connection = mysql.connector.connect(
        user='your_user',
        password='your_password',
        host='127.0.0.1',
        database='your_database'
    )
except mysql.connector.Error as err:
    if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
        print("Something is wrong with your user name or password")
    elif err.errno == errorcode.ER_BAD_DB_ERROR:
        print("Database does not exist")
    else:
        print(err)
    exit(1) # Exit if connection fails
# ----------------------------------------------------------------

def add(name, age):
    """
    Add name, age to students table in MySQL.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    # The query uses %s as a placeholder, which is standard for this library
    query = "INSERT INTO students (name, age) VALUES (%s, %s)"
    
    # The values to be inserted are passed as a tuple
    values = (name, age)

    try:
        # A cursor is used to execute queries. The 'with' statement ensures it's closed.
        with connection.cursor() as cursor:
            # Execute the query
            cursor.execute(query, values)

            # The connection.commit() is crucial to save the changes to the database
            connection.commit()

            # The JavaScript `console.log` is equivalent to Python's `print`
            # Using an f-string is a modern and readable way to format strings
            print(f"Added {name} to students table")

    except mysql.connector.Error as err:
        # The JavaScript `if (err) throw err;` is equivalent to catching an exception
        print(f"Failed to add record: {err}")
        # Rollback the transaction in case of an error
        connection.rollback()
        # Re-raise the exception to halt the program, similar to `throw err`
        raise

# --- Example Usage and Cleanup ---
if __name__ == "__main__":
    try:
        # Example calls to the function
        add("Alice", 22)
        add("Bob", 25)
    except Exception as e:
        print(f"An error occurred during the operation: {e}")
    finally:
        # It's good practice to close the connection when the script is finished
        if connection.is_connected():
            connection.close()
            print("MySQL connection is closed.")