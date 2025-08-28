import mysql.connector

# Establish a connection to the database
con = mysql.connector.connect(
    host="your_host",  # replace with your host
    user="your_username",  # replace with your username
    password="your_password",  # replace with your password
    database="your_database"  # replace with your database name
)

def add_student(name, age):
    try:
        cursor = con.cursor()
        query = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"
        cursor.execute(query, (name, age))
        con.commit()
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        cursor.close()

# Don't forget to close the connection when you're done
# con.close()