import mysql.connector

def insert_student(name, age):
    # Assuming config is defined elsewhere with database connection parameters
    # config should contain: host, user, password, database
    connection = mysql.connector.connect(**config)
    
    cursor = connection.cursor()
    
    # insert statement
    sql = """INSERT INTO STUDENTS(title,completed)
             VALUES('Learn how to insert a new row',true)"""
    
    # execute the insert statement
    cursor.execute(sql)
    
    # commit the transaction
    connection.commit()
    
    cursor.close()
    connection.close()

insert_student("XXXXX", 0)