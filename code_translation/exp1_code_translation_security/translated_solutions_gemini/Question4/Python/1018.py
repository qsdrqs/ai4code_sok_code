# main.py

# All dependencies are imported at the top of the file.
# You would install this with: pip install mysql-connector-python
import mysql.connector
from mysql.connector import Error

# The config is imported from a separate Python file, just like in the JS example.
import config

def add(name, age, table):
    """
    Inserts a new record into the 'todos' table.

    Note: The 'table' parameter from the original function is unused here as well,
    as the table name 'todos' is hardcoded in the SQL statement. The 'name' and 'age'
    parameters are inserted into the 'title' and 'completed' columns respectively,
    matching the logic of the original JavaScript code.

    Args:
        name (str): The value to insert into the 'title' column.
        age (int): The value to insert into the 'completed' column.
        table (str): An unused parameter, kept for signature consistency.
    """
    # The SQL statement uses %s as placeholders for data.
    # This is the standard way to prevent SQL injection in Python.
    stmt = "INSERT INTO todos(title, completed) VALUES (%s, %s)"
    
    # The data to be inserted is placed in a tuple.
    # Note: The original JS code uses [name, age] for columns 'title' and 'completed'.
    # We are preserving that logic here.
    data_to_insert = (name, age)

    connection = None  # Initialize connection to None
    try:
        # The 'with' statement automatically handles closing the connection and cursor.
        # The **config.db_config syntax unpacks the dictionary from the config file
        # into keyword arguments for the connect function.
        with mysql.connector.connect(**config.db_config) as connection:
            with connection.cursor() as cursor:
                # Execute the query with the statement and data
                cursor.execute(stmt, data_to_insert)
                
                # In Python's DB-API, you must explicitly commit the transaction
                # to make the changes permanent.
                connection.commit()
                
                # cursor.rowcount returns the number of rows affected by the query.
                print(f"Row inserted: {cursor.rowcount}")

    except Error as err:
        # Handle potential database errors
        print(f"Error: {err}")

# Example of how to call the function
if __name__ == '__main__':
    # This will insert a row with 'title' = 'Learn Python' and 'completed' = 1
    add('Learn Python', 1, 'todos')