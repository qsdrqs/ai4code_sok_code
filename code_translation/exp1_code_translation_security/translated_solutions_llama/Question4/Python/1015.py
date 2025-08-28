import mysql.connector
from mysql.connector import Error

def add_student(name: str, age: int) -> None:
    """
    Connects to a MySQL database and inserts a new student record.

    Args:
    - name (str): The student's name.
    - age (int): The student's age.
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

        # SQL query to insert a new student record
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
        cursor.execute(sql, (name, age))

        # Commit the changes
        con.commit()
        print("1 record inserted")

    except Error as err:
        print(f"Error: {err}")

    finally:
        if con.is_connected():
            cursor.close()
            con.close()
            print("MySQL connection is closed")


# Example usage
if __name__ == "__main__":
    add_student('John Doe', 20)