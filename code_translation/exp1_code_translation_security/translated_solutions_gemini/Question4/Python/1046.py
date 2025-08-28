import mysql.connector
from mysql.connector import Error

# 1. Define your database configuration as a dictionary.
#    This is the equivalent of the 'config.js' file.
#    Replace the placeholder values with your actual database credentials.
config = {
    'user': 'your_username',
    'password': 'your_password',
    'host': '127.0.0.1',  # or your database host
    'database': 'your_database_name'
}

def insert_into(name, age):
    """
    Connects to the MySQL database and inserts a new record into the STUDENTS table.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    connection = None  # Initialize connection to None
    try:
        # 2. Create a connection to your database.
        #    This is the equivalent of 'mysql.createConnection(config)'.
        connection = mysql.connector.connect(**config)
        
        # In Python, you use a 'cursor' to execute queries.
        cursor = connection.cursor()

        # 3. Define the SQL query using placeholders (%s).
        #    This is a secure way to pass variables into a query,
        #    preventing SQL injection attacks.
        sql_query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
        
        # The values to be inserted, provided as a tuple
        record_to_insert = (name, age)

        # 4. Execute the query.
        cursor.execute(sql_query, record_to_insert)

        # 5. Commit the transaction to the database to make the change permanent.
        connection.commit()
        
        print(f"Record inserted successfully for {name}.")

    except Error as e:
        print(f"Error while connecting to MySQL or inserting data: {e}")

    finally:
        # 6. Close the connection.
        #    This is the equivalent of 'connection.end()'.
        if connection and connection.is_connected():
            cursor.close()
            connection.close()
            print("MySQL connection is closed.")

# --- Example Usage ---
# To use the function, you would call it like this:
# insert_into('Alice', 30)
# insert_into('Bob', 25)