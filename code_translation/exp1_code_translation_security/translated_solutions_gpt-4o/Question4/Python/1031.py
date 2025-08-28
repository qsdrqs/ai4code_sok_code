import mysql.connector

def insert(name, age):
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword",
        database="STUDENTS"
    )

    cursor = con.cursor()

    try:
        print("Connected!")
        sql = "INSERT INTO students (name, age) VALUES (%s, %s)"
        values = (name, age)
        cursor.execute(sql, values)
        con.commit()
        print("1 record inserted")
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        cursor.close()
        con.close()