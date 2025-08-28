import mysql.connector

def insertion(name, age):
    # Create a connection to the database
    con = mysql.connector.connect(
        host="your_host",  # Replace with your database host
        user="your_user",  # Replace with your database username
        password="your_password",  # Replace with your database password
        database="your_database"  # Replace with your database name
    )
    
    try:
        cursor = con.cursor()
        # SQL query to insert data into the STUDENTS table
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
        values = (name, age)
        cursor.execute(sql, values)
        con.commit()  # Commit the transaction
        print("Record inserted successfully")
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        cursor.close()
        con.close()