import mysql.connector

def add_student(name, age):
    # Establish a connection to the database
    connection = mysql.connector.connect(
        host='your_host',  # e.g., 'localhost'
        user='your_username',
        password='your_password',
        database='your_database'
    )

    try:
        cursor = connection.cursor()
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
        values = (name, age)
        cursor.execute(sql, values)
        connection.commit()
        print("1 record inserted")
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        cursor.close()
        connection.close()

# Example usage
# add_student('John Doe', 20)