import mysql.connector

def add_student(name, age):
    # Assuming 'connection' is a mysql.connector connection object
    cursor = connection.cursor()
    query = "INSERT INTO STUDENTS(NAME, AGE) VALUES(%s, %s)"
    values = (name, age)
    try:
        cursor.execute(query, values)
        connection.commit()
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        cursor.close()

# Example usage:
# connection = mysql.connector.connect(user='yourusername', password='yourpassword',
#                                      host='yourhost', database='yourdatabase')
# add_student('John Doe', 20)