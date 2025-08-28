import mysql.connector
from mysql.connector import Error

def insertion(name, age):
    """
    Inserts a new student into the STUDENTS table.

    Args:
        name (str): The student's name.
        age (int): The student's age.
    """

    # Establish a connection to the database
    try:
        connection = mysql.connector.connect(
            host='localhost',
            database='your_database',
            user='your_username',
            password='your_password'
        )

        # Create a cursor object
        cursor = connection.cursor()

        # SQL query to insert data
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"

        # Execute the query
        cursor.execute(sql, (name, age))

        # Commit the changes
        connection.commit()

        print("Student inserted successfully.")

    except Error as e:
        print(f"Error: '{e}'")

    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()
            print("MySQL connection is closed.")

# Example usage
if __name__ == "__main__":
    insertion("John Doe", 20)