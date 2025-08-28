import mysql.connector
from mysql.connector import Error

def inserter(name, age):
    try:
        # Create connection
        con = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="mydb"
        )
        
        if con.is_connected():
            print("Connected!")
            
            cursor = con.cursor()
            sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
            cursor.execute(sql, (name, age))
            
            # Commit the transaction
            con.commit()
            print("1 record inserted")
            
    except Error as err:
        print(f"Error: {err}")
        raise err
    
    finally:
        # Close cursor and connection
        if 'cursor' in locals():
            cursor.close()
        if con.is_connected():
            con.close()