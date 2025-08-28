import mysql.connector

def create_entry(name, age):
    try:
        con = mysql.connector.connect(
            host='your_host',  # replace with your host
            user='your_username',  # replace with your username
            password='your_password',  # replace with your password
            database='your_database'  # replace with your database name
        )

        cursor = con.cursor()
        query = 'INSERT INTO STUDENTS (name, age) VALUES (%s, %s)'
        cursor.execute(query, (name, age))
        con.commit()

    except mysql.connector.Error as err:
        print(f"Error: {err}")
    
    finally:
        if con.is_connected():
            cursor.close()
            con.close()