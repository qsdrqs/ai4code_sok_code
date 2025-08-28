import mysql.connector
from mysql.connector import errorcode

def add_student(name: str, age: int, connection):
    """
    Adds a new student to the database.

    Args:
    - name (str): The student's name.
    - age (int): The student's age.
    - connection: A mysql db connection object.
    """
    query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    values = (name, age)

    try:
        cursor = connection.cursor()
        cursor.execute(query, values)
        connection.commit()
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
            print("Something is wrong with your user name or password")
        elif err.errno == errorcode.ER_BAD_DB_ERROR:
            print("Database does not exist")
        else:
            print(err)

# Example usage:
if __name__ == "__main__":
    try:
        connection = mysql.connector.connect(
            user='your_username',
            password='your_password',
            host='your_host',
            database='your_database'
        )
        add_student('John Doe', 20, connection)
    except mysql.connector.Error as err:
        print(err)
    finally:
        if 'connection' in locals():
            connection.close()