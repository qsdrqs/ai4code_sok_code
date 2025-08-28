import mysql.connector
from mysql.connector import Error

# Create connection (replace ... with actual connection parameters)
con = mysql.connector.connect(
    # host='localhost',
    # database='your_database',
    # user='your_username',
    # password='your_password'
)

def add_student(name, age):
    try:
        if con.is_connected():
            cursor = con.cursor()
            cursor.execute("INSERT INTO STUDENTS VALUES (%s, %s)", (name, age))
            con.commit()
            cursor.close()
    except Error as err:
        raise err