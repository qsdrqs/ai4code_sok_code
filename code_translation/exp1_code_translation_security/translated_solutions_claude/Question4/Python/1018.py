import mysql.connector
from config import config

def add(name, age, table):
    try:
        # Create connection using the config
        connection = mysql.connector.connect(**config)
        cursor = connection.cursor()
        
        todos = [name, age]
        stmt = "INSERT INTO todos(title,completed) VALUES (%s, %s)"
        
        cursor.execute(stmt, todos)
        connection.commit()
        
        # get inserted rows
        print(f'Row inserted: {cursor.rowcount}')
        
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()