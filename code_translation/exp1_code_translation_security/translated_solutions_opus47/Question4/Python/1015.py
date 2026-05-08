import mysql.connector

def add_student(name, age):
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword",
        database="mydb"
    )
    print("Connected!")
    
    cursor = con.cursor()
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + str(name) + "', '" + str(age) + "')"
    cursor.execute(sql)
    con.commit()
    print("1 record inserted")