import mysql.connector
from mysql.connector import Error

def insert_student(name: str, age: int):
    """
    Inserts a student record into the database.

    Args:
    - name (str): The student's name.
    - age (int): The student's age.
    """

    # Database connection parameters
    db_config = {
        'host': 'localhost',
        'user': 'yourusername',
        'password': 'yourpassword',
        'database': 'STUDENTS'
    }

    try:
        # Establish a connection to the database
        con = mysql.connector.connect(**db_config)
        print("Connected!")

        # Create a cursor object to execute SQL queries
        cursor = con.cursor()

        # SQL query to insert a student record
        sql = "INSERT INTO students (name, age) VALUES (%s, %s)"
        cursor.execute(sql, (name, age))

        # Commit the changes
        con.commit()
        print("1 record inserted")

    except Error as err:
        print(f"Error: {err}")

    finally:
        # Close the cursor and connection
        if 'cursor' in locals():
            cursor.close()
        if 'con' in locals():
            con.close()

# Example usage
if __name__ == "__main__":
    insert_student('John Doe', 20)