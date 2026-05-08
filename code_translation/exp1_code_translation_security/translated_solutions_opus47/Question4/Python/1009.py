import mysql.connector

def create_entry(name, age):
    try:
        cursor = con.cursor()
        cursor.execute('INSERT INTO STUDENTS (name, age) VALUES (%s, %s)', (name, age))
        con.commit()
        cursor.close()
    except mysql.connector.Error as err:
        raise err