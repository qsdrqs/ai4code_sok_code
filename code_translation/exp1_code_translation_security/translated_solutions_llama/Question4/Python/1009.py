import mysql.connector
from mysql.connector import Error

# Assuming you have a connection object or a function to create one
def create_connection(host_name, db_name, user_name, user_password):
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

def create_entry(con, name, age):
    try:
        cursor = con.cursor()
        query = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"
        cursor.execute(query, (name, age))
        con.commit()
        print("Entry created successfully")
    except Error as err:
        print(f"Error: '{err}'")

# Example usage
if __name__ == "__main__":
    host_name = 'your_host'
    db_name = 'your_database'
    user_name = 'your_username'
    user_password = 'your_password'

    con = create_connection(host_name, db_name, user_name, user_password)
    if con:
        create_entry(con, 'John Doe', 25)
        con.close()