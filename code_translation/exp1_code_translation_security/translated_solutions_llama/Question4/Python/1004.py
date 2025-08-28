import mysql.connector
from mysql.connector import Error

# Define a function to add a student to the STUDENTS table
def add_student(connection, name, age):
    """
    Add a student to the STUDENTS table.

    Args:
        connection: The MySQL connection object.
        name (str): The name of the student.
        age (int): The age of the student.
    """
    query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    cursor = connection.cursor()
    try:
        cursor.execute(query, (name, age))
        connection.commit()
        print("Result: Student added successfully!")
    except Error as e:
        print(f"Error: {e}")
    finally:
        cursor.close()

# Establish a connection to the MySQL database
def connect_to_database(host, user, password):
    try:
        connection = mysql.connector.connect(
            host=host,
            user=user,
            password=password
        )
        print("Connected!")
        return connection
    except Error as e:
        print(f"Error: {e}")
        return None

# Main function
def main():
    # Database connection parameters
    host = "localhost"
    user = "yourusername"
    password = "yourpassword"

    # Establish a connection to the database
    connection = connect_to_database(host, user, password)

    if connection:
        # Add a student to the STUDENTS table
        add_student(connection, "XXXXX", 0)

        # Close the connection
        connection.close()

if __name__ == "__main__":
    main()