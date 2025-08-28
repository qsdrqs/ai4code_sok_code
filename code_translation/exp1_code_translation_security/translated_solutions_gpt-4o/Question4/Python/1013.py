import mysql.connector

def insert():
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword",
        database="mydb"
    )

    cursor = con.cursor()

    try:
        print("Connected!")
        sql = "INSERT INTO students (name, age) VALUES (%s, %s)"
        name = input("Enter a name: ")
        age = int(input("Enter an age: "))
        values = (name, age)
        cursor.execute(sql, values)
        con.commit()
        print("Number of records inserted:", cursor.rowcount)
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        con.close()

# Call the insert function
insert()