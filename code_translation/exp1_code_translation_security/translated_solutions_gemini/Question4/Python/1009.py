# 1. Import the necessary library
# You may need to install it first: pip install mysql-connector-python
import mysql.connector
from mysql.connector import Error

# 2. Establish the database connection
# This part is assumed to be handled outside the function, similar to the 'con'
# object in the JavaScript example.
try:
    # Replace with your actual database credentials
    con = mysql.connector.connect(
        host='localhost',
        database='your_database_name',
        user='your_username',
        password='your_password'
    )

except Error as e:
    print(f"Error connecting to MySQL: {e}")
    # In a real application, you would handle this more gracefully
    raise

# 3. The translated function
def create_entry(name, age):
    """
    Inserts a new entry into the STUDENTS table.
    """
    # The SQL query uses %s as a placeholder, which is the standard for this library
    sql_query = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"
    data = (name, age)
    
    # A cursor is used to execute queries in Python
    cursor = None
    try:
        # Create a cursor object
        cursor = con.cursor()
        
        # Execute the query with the data
        cursor.execute(sql_query, data)
        
        # Commit the transaction to make the changes permanent
        con.commit()
        
        print(f"Successfully added entry for {name}, age {age}")

    except Error as err:
        print(f"Error: {err}")
        # If an error occurs, rollback any changes
        con.rollback()
        # Re-raise the exception to notify the caller, similar to 'throw err'
        raise
        
    finally:
        # Ensure the cursor is closed, whether the query succeeded or failed
        if cursor:
            cursor.close()

# --- Example Usage ---
# if __name__ == "__main__":
#     try:
#         create_entry("Alice", 22)
#         create_entry("Bob", 25)
#     finally:
#         # Close the connection when the application is done
#         if con.is_connected():
#             con.close()
#             print("MySQL connection is closed")