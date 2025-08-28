import pyodbc

# Define the function to insert a user
def insert_user(conn, name, age):
    """
    Inserts a new user into the STUDENTS table.

    Args:
        conn (pyodbc.Connection): The active database connection.
        name (str): The name of the user.
        age (int): The age of the user.
    """
    # Create a cursor object
    cursor = conn.cursor()

    # SQL query with parameterized query
    query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

    try:
        # Execute the query with parameters
        cursor.execute(query, name, age)
        # Commit the changes
        conn.commit()
    except pyodbc.Error as err:
        # Handle any errors that occur
        print(err)
    finally:
        # Close the cursor
        cursor.close()

# Example usage
if __name__ == "__main__":
    # Define the connection string
    # Replace with your actual connection string
    conn_str = "DRIVER={ODBC Driver 17 for SQL Server};SERVER=your_server;DATABASE=your_database;UID=your_username;PWD=your_password"

    # Establish a connection
    conn = pyodbc.connect(conn_str)

    # Insert a new user
    insert_user(conn, "John Doe", 30)

    # Close the connection
    conn.close()