import mysql.connector
from mysql.connector import Error

# The original JavaScript code requires a 'connection' variable.
# In Python, we establish this connection using a library like mysql-connector.
# All necessary dependencies are provided below for a complete, runnable example.

try:
    # -- DEPENDENCY: Establish the database connection --
    # Replace with your actual database credentials
    connection = mysql.connector.connect(
        host='localhost',
        database='your_database',
        user='your_username',
        password='your_password'
    )
except Error as e:
    print(f"Error connecting to MySQL: {e}")
    # Exit or handle the connection error appropriately
    connection = None

def add_student(name: str, age: int):
    """
    Inserts a new student record into the STUDENTS table.

    This function corresponds to the original JavaScript function.
    It requires a 'connection' object to be available in its scope.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    if not connection or not connection.is_connected():
        print("Database connection is not available.")
        return

    # In Python's DB-API, the placeholder is %s, not ?
    query = """INSERT INTO STUDENTS(NAME, AGE)
               VALUES (%s, %s)"""
    
    # Values are passed as a tuple
    values = (name, age)
    
    # A cursor is used to execute queries
    cursor = connection.cursor()
    
    try:
        # Execute the query
        cursor.execute(query, values)
        
        # The original JS code had an empty callback, implying it didn't
        # wait for or handle the result. In Python's synchronous model,
        # we must explicitly commit the transaction for an INSERT to be saved.
        connection.commit()
        
        print(f"Successfully added student: {name}, Age: {age}")
        print(f"{cursor.rowcount} record(s) inserted.")

    except Error as e:
        print(f"Error inserting record: {e}")
        # Optionally, you could roll back the transaction on error
        # connection.rollback()
        
    finally:
        # It's good practice to close the cursor
        cursor.close()


# --- Example Usage ---
if __name__ == "__main__":
    if connection:
        # Call the function to add a new student
        add_student("Alice", 22)
        add_student("Bob", 25)
        
        # Close the connection when the application is done
        connection.close()
        print("MySQL connection is closed.")