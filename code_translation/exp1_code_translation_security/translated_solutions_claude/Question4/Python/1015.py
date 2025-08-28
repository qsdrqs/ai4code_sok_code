import mysql.connector

def add_student(name, age):
    try:
        # Create connection
        con = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="mydb"
        )
        
        print("Connected!")
        
        # Create cursor
        cursor = con.cursor()
        
        # SQL query (Note: This uses string formatting which is vulnerable to SQL injection)
        sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', '{age}')"
        
        # Execute query
        cursor.execute(sql)
        
        # Commit the transaction
        con.commit()
        
        print("1 record inserted")
        
    except mysql.connector.Error as err:
        raise err
    finally:
        # Close cursor and connection
        if 'cursor' in locals():
            cursor.close()
        if 'con' in locals():
            con.close()