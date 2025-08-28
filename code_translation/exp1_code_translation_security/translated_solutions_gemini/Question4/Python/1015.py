# The mysql-connector-python library is required.
# You can install it via pip: pip install mysql-connector-python
import mysql.connector
from mysql.connector import Error

def add_student(name, age):
    """
    Connects to a MySQL database and inserts a new student record.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    connection = None
    cursor = None
    try:
        # Establish the database connection
        connection = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="mydb"
        )

        if connection.is_connected():
            print("Connected to MySQL database")
            cursor = connection.cursor()
            
            # IMPORTANT: Use parameterized queries to prevent SQL injection.
            # The '%s' is a placeholder, not a Python string format operator here.
            sql_query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
            
            # The values to insert are passed as a tuple
            record_to_insert = (name, age)
            
            cursor.execute(sql_query, record_to_insert)
            
            # Commit the transaction to make the changes permanent
            connection.commit()
            
            print(f"{cursor.rowcount} record inserted successfully.")

    except Error as e:
        # Handle potential errors during connection or query execution
        print(f"Error: {e}")

    finally:
        # Ensure the cursor and connection are closed
        if cursor:
            cursor.close()
        if connection and connection.is_connected():
            connection.close()
            print("MySQL connection is closed")

# --- Example Usage ---
if __name__ == "__main__":
    # This code will only run when the script is executed directly
    add_student("John Doe", 21)
    add_student("Jane Smith", 23)