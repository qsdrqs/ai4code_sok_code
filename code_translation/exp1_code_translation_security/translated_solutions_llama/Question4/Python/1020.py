import mysql.connector
from mysql.connector import Error

def inserter(name, age):
    """
    Inserts a record into the STUDENTS table.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """

    # Database connection parameters
    db_config = {
        'host': 'localhost',
        'user': 'yourusername',
        'password': 'yourpassword',
        'database': 'mydb'
    }

    try:
        # Establish a connection to the database
        con = mysql.connector.connect(**db_config)
        cursor = con.cursor()

        # SQL query to insert a record into the STUDENTS table
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
        values = (name, age)

        # Execute the SQL query
        cursor.execute(sql, values)
        con.commit()

        print("1 record inserted")

    except Error as e:
        print(f"Error: {e}")

    finally:
        if con.is_connected():
            cursor.close()
            con.close()
            print("Connected closed")

# Example usage
if __name__ == "__main__":
    inserter('John Doe', 25)