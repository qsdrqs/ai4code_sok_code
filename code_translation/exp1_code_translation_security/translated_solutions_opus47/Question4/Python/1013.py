import mysql.connector

con = mysql.connector.connect(
    host="localhost",
    user="yourusername",
    password="yourpassword",
    database="mydb"
)

def insert():
    try:
        print("Connected!")
        sql = "INSERT INTO students (name, age) VALUES (%s, %s)"
        name = input("enter a name: ")
        age = int(input("enter an age: "))
        values = [(name, age)]
        cursor = con.cursor()
        cursor.executemany(sql, values)
        con.commit()
        print("Number of records inserted: " + str(cursor.rowcount))
    except mysql.connector.Error as err:
        raise err