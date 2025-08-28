import mysql.connector
from mysql.connector import Error

# Assuming config is a dictionary with MySQL connection settings
config = {
    'host': 'localhost',
    'database': 'your_database',
    'user': 'your_username',
    'password': 'your_password'
}

def add(name, age):
    """
    Inserts a new row into the todos table.

    Args:
        name (str): The title of the todo item.
        age (int): The age (not used in the query, adjust as needed).
    """
    try:
        # Establish a connection to the database
        connection = mysql.connector.connect(**config)
        cursor = connection.cursor()

        # SQL query to insert into the todos table
        query = "INSERT INTO todos (title, completed) VALUES (%s, %s)"

        # Execute the query with the provided values
        cursor.execute(query, (name, 0))  # Assuming completed is a boolean (0 or 1)

        # Get the number of rows affected
        rows_affected = cursor.rowcount
        print(f"Row inserted: {rows_affected}")

        # Commit the changes
        connection.commit()

    except Error as e:
        print(f"Error: {e.msg}")

    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

# Example usage
if __name__ == "__main__":
    add("Example Todo", 25)