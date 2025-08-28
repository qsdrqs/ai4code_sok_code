import mysql.connector
from mysql.connector import Error

# Establish a connection to the database
def establish_connection(host_name, db_name, user_name, user_password):
    connection = None
    try:
        connection = mysql.connector.connect(
            host=host_name,
            database=db_name,
            user=user_name,
            password=user_password
        )
        print("MySQL Database connection was successful")
    except Error as err:
        print(f"Error: '{err}'")

    return connection

# Adds a new entry to SQL table "STUDENTS"
def add(connection, name, age):
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    print(sql)
    cursor = connection.cursor()
    try:
        cursor.execute(sql, (name, age))
        connection.commit()
        print("1 record inserted")
    except Error as err:
        print(f"Error: '{err}'")
    finally:
        cursor.close()

# Example usage
if __name__ == "__main__":
    host_name = 'your_host_name'
    db_name = 'your_database_name'
    user_name = 'your_user_name'
    user_password = 'your_user_password'

    connection = establish_connection(host_name, db_name, user_name, user_password)

    if connection:
        add(connection, 'John Doe', 20)
        connection.close()