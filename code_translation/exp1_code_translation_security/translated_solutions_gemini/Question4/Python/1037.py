import mysql.connector
from mysql.connector import errorcode

# In Python, you would typically define your database configuration in a dictionary.
# This 'config' variable was implied but not defined in the original JS.
config = {
  'user': 'your_username',
  'password': 'your_password',
  'host': '127.0.0.1',
  'database': 'your_database_name'
}

def insert_student(name, age):
    """
    Connects to a MySQL database and inserts a hardcoded row.
    NOTE: This is a direct translation. The 'name' and 'age' parameters are
    not used in the SQL query, just like in the original JavaScript code.
    """
    connection = None
    try:
        # Equivalent to mysql.createConnection(config)
        connection = mysql.connector.connect(**config)
        cursor = connection.cursor()

        # The SQL statement is hardcoded, same as the original JS snippet.
        # Note that boolean 'true' in JS is 'True' in Python.
        sql = "INSERT INTO STUDENTS(title, completed) VALUES('Learn how to insert a new row', True)"

        # Execute the insert statement
        cursor.execute(sql)

        # In Python's DB-API, you must commit the transaction to save changes.
        connection.commit()
        
        print(f"Row inserted successfully. Last inserted ID: {cursor.lastrowid}")

    except mysql.connector.Error as err:
        print(f"An error occurred: {err}")

    finally:
        # Equivalent to connection.end()
        if connection and connection.is_connected():
            cursor.close()
            connection.close()
            print("MySQL connection is closed.")

# Call the function
insert_student("XXXXX", 0)