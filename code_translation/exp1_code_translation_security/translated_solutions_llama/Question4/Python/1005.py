import mysql.connector
from mysql.connector import Error

# Define database connection parameters
config = {
    'host': 'your_host',
    'database': 'your_database',
    'user': 'your_user',
    'password': 'your_password'
}

def add_student(name, age):
    try:
        # Establish a connection to the database
        con = mysql.connector.connect(**config)
        cursor = con.cursor()

        # Execute the INSERT query
        query = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"
        cursor.execute(query, (name, age))

        # Commit the changes
        con.commit()
        print("Student added successfully")

    except Error as e:
        print(f"Error: '{e}'")

    finally:
        if con.is_connected():
            cursor.close()
            con.close()
            print("MySQL connection is closed")

# Example usage
if __name__ == "__main__":
    add_student('John Doe', 20)