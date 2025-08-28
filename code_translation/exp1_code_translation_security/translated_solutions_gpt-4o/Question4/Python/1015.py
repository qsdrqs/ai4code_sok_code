import mysql.connector

def add_student(name, age):
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword",
        database="mydb"
    )

    try:
        if con.is_connected():
            print("Connected!")
            cursor = con.cursor()
            sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
            values = (name, age)
            cursor.execute(sql, values)
            con.commit()
            print("1 record inserted")
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        if con.is_connected():
            cursor.close()
            con.close()

# Example usage
# add_student("John Doe", 25)