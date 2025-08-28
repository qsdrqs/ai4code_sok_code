# This code has not been TESTED!!

# The 'mysql-connector-python' library is the Python equivalent of Node.js's 'mysql' package.
# You can install it using pip:
# pip install mysql-connector-python

import mysql.connector
from mysql.connector import Error

def insert_records():
    """
    Connects to a MySQL database and inserts multiple records into the 'customers' table.
    """
    connection = None  # Initialize connection to None
    cursor = None      # Initialize cursor to None
    try:
        # Establish the database connection
        connection = mysql.connector.connect(
            host="127.0.0.1",
            user="yourusername",
            password="yourpassword",
            database="mydb"
        )

        if connection.is_connected():
            print("Connected to MySQL database!")

            # In Python, you use a cursor to execute queries.
            cursor = connection.cursor()

            # The SQL query uses %s as a placeholder, which is the standard for this library.
            sql_query = "INSERT INTO customers (name, address) VALUES (%s, %s)"

            # A list of tuples or lists, where each inner item corresponds to a row to be inserted.
            values = [
                ('XXXXX', 'XXXXX'),
                # You can add more records here to insert them all at once
                # ('Peter', 'Lowstreet 4'),
                # ('Amy', 'Apple st 652')
            ]

            # Use executemany() to insert multiple rows.
            # This is more efficient than executing a query for each row.
            cursor.executemany(sql_query, values)

            # In Python's DB-API, you must explicitly commit the transaction to save changes.
            connection.commit()

            # cursor.rowcount gives the number of affected rows.
            print(f"{cursor.rowcount} records inserted successfully.")

    except Error as e:
        # Python's standard way of handling errors is with a try...except block.
        print(f"Error: {e}")

    finally:
        # The 'finally' block ensures that the connection is closed,
        # even if an error occurred. This is a crucial best practice.
        if connection and connection.is_connected():
            if cursor:
                cursor.close()
            connection.close()
            print("MySQL connection is closed.")

# Run the function
if __name__ == "__main__":
    insert_records()