import pyodbc

def add_student(name: str, age: int) -> None:
    """
    Adds a student to the STUDENTS table in the database.

    Args:
    - name (str): The name of the student.
    - age (int): The age of the student.
    """

    # Define the connection string
    # Replace with your actual database connection details
    connection_string = "DRIVER={ODBC Driver 17 for SQL Server};SERVER=your_server;DATABASE=your_database;UID=your_username;PWD=your_password"

    try:
        # Establish a connection to the database
        connection = pyodbc.connect(connection_string)
        cursor = connection.cursor()

        # SQL query to insert a student
        query = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)"

        # Execute the query with the provided parameters
        cursor.execute(query, name, age)

        # Commit the changes
        connection.commit()

        print("Student added successfully.")

    except pyodbc.Error as e:
        print(f"An error occurred: {e}")

    finally:
        # Close the cursor and connection
        if 'cursor' in locals():
            cursor.close()
        if 'connection' in locals():
            connection.close()


# Example usage
if __name__ == "__main__":
    add_student("John Doe", 20)