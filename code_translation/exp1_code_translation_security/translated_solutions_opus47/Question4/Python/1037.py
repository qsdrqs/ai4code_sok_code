import mysql.connector

def insert_student(name, age):
    connection = mysql.connector.connect(**config)
    
    # insert statement
    sql = """INSERT INTO STUDENTS(title, completed)
             VALUES('Learn how to insert a new row', true)"""
    
    # execute the insert statement
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    
    cursor.close()
    connection.close()

insert_student("XXXXX", 0)