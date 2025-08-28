import mysql.connector

# Establish a connection to the MySQL database
# Replace the placeholders with your actual database credentials
connection = mysql.connector.connect(
    host='localhost',
    user='your_username',
    password='your_password',
    database='students'
)

def add(name, age):
    """
    Inserts a student's name and age into the 'students' table.
    
    Parameters:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    query = "INSERT INTO students (name, age) VALUES (%s, %s)"
    cursor = connection.cursor()
    try:
        cursor.execute(query, (name, age))
        connection.commit()
        print(f"Added {name} to students table")
    finally:
        cursor.close()