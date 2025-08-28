import mysql.connector
from mysql.connector import Error

# Establish a connection to the database
def establish_connection(host_name, db_name, user_name, user_password):
    connection = None
    try:
        connection = mysql.connector.connect(
            host=host_name,
            database=db_name,
            user=user_name,
            password=user_password
        )
        print("MySQL Database connection was successful")
    except Error as err:
        print(f"Error: '{err}'")

    return connection

# Function to add a student to the database
def add_student(connection, name, age):
    """Add name, age to students table in MySQL"""
    query = "INSERT INTO students (name, age) VALUES (%s, %s)"
    try:
        cursor = connection.cursor()
        cursor.execute(query, (name, age))
        connection.commit()
        print(f"Added {name} to students table")
    except Error as err:
        print(f"Error: '{err}'")

# Example usage
if __name__ == "__main__":
    host_name = 'your_host_name'
    db_name = 'your_database_name'
    user_name = 'your_user_name'
    user_password = 'your_user_password'

    connection = establish_connection(host_name, db_name, user_name, user_password)
    if connection:
        add_student(connection, 'John Doe', 25)
        connection.close()