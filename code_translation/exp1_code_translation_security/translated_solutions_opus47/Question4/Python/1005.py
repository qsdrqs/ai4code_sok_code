import mysql.connector

con = mysql.connector.connect(...)

def add_student(name, age):
    try:
        if not con.is_connected():
            con.reconnect()
        cursor = con.cursor()
        cursor.execute(f"INSERT INTO STUDENTS VALUES ({name}, {age})")
        con.commit()
        cursor.close()
    except mysql.connector.Error as err:
        raise err