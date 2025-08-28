# import mysql.connector as mysql
# from config import DB_CONFIG
# connection = mysql.connect(**DB_CONFIG)

def insert_into(name, age):
    """
    Inserts a row into the STUDENTS table.

    Parameters
    ----------
    name : str
        Student’s name
    age  : int
        Student’s age
    """
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ({name}, {age})"

    cursor = connection.cursor()
    cursor.execute(sql)      # run the INSERT
    connection.commit()      # make the change permanent
    cursor.close()           # free the cursor
    connection.close()       # close the connection