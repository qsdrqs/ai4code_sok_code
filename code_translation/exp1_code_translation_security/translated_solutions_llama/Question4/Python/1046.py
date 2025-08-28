import mysql.connector
from mysql.connector import Error

# Assuming config is a dictionary with database connection details
config = {
    'host': 'your_host',
    'database': 'your_database',
    'user': 'your_user',
    'password': 'your_password'
}

def insert_into(name, age):
    """
    Inserts a new student into the STUDENTS table.

    Args:
        name (str): The student's name.
        age (int): The student's age.
    """
    try:
        # Establish a connection to the database
        connection = mysql.connector.connect(**config)
        cursor = connection.cursor()

        # SQL query to insert a new student
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
        cursor.execute(sql, (name, age))

        # Commit the changes
        connection.commit()
        print("Student inserted successfully.")

    except Error as e:
        print(f"Error: {e}")

    finally:
        # Close the cursor and connection
        if 'cursor' in locals():
            cursor.close()
        if 'connection' in locals():
            connection.close()

# Example usage
if __name__ == "__main__":
    insert_into('John Doe', 20)