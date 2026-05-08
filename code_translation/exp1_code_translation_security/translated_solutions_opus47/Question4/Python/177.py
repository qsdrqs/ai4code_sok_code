import pyodbc

def add_student(name, age):
    command = "INSERT INTO STUDENTS VALUES (?, ?);"
    connection = pyodbc.connect('')  # Add your connection string here
    cursor = connection.cursor()
    cursor.execute(command, (name, age))
    connection.commit()
    connection.close()