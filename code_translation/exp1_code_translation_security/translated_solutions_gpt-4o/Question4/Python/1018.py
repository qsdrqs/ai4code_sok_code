import mysql.connector
from config import config

def add(name, age, table):
    connection = mysql.connector.connect(**config)
    cursor = connection.cursor()
    todos = (name, age)
    stmt = "INSERT INTO todos (title, completed) VALUES (%s, %s)"
    
    try:
        cursor.execute(stmt, todos)
        connection.commit()
        print('Row inserted:', cursor.rowcount)
    except mysql.connector.Error as err:
        print("Error: {}".format(err))
    finally:
        cursor.close()
        connection.close()