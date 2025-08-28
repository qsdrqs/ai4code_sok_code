import mysql.connector
import sys

# Create connection
con = mysql.connector.connect(
    host="localhost",
    user="yourusername",
    password="yourpassword",
    database="mydb"
)

def readLine(prompt):
    """Helper function to read a line of input"""
    return input(prompt)

def readInt(prompt):
    """Helper function to read an integer input"""
    while True:
        try:
            return int(input(prompt))
        except ValueError:
            print("Please enter a valid integer.")

def insert():
    try:
        print("Connected!")
        cursor = con.cursor()
        sql = "INSERT INTO students (name, age) VALUES (%s, %s)"
        name = readLine("enter a name: ")
        age = readInt("enter an age: ")
        values = (name, age)
        
        cursor.execute(sql, values)
        con.commit()  # Important: commit the transaction
        
        print(f"Number of records inserted: {cursor.rowcount}")
        cursor.close()
        
    except mysql.connector.Error as err:
        print(f"Error: {err}")
        sys.exit(1)