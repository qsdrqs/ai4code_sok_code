import mysql.connector
from mysql.connector import Error

def register_student(name: str, age: int) -> None:
    """
    Registers a student in the database.

    Args:
    - name (str): The student's name.
    - age (int): The student's age.
    """

    # Establish a connection to the database
    try:
        connection = mysql.connector.connect(
            host='your_host',
            database='your_database',
            user='your_user',
            password='your_password'
        )
        
        # Create a cursor object to execute SQL queries
        cursor = connection.cursor()

        # Prepare the SQL query
        query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"

        # Execute the query with the provided student details
        cursor.execute(query, (name, age))

        # Commit the changes
        connection.commit()
        print("Student registered successfully.")

    except Error as e:
        print(f"Error registering student: {e}")

    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

# Example usage
if __name__ == "__main__":
    register_student('John Doe', 20)