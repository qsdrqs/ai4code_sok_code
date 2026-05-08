import mysql.connector

def inserter(name, age):
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword",
        database="mydb"
    )
    
    print("Connected!")
    cursor = con.cursor()
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    values = (name, age)
    cursor.execute(sql, values)
    con.commit()
    print("1 record inserted")
    cursor.close()
    con.close()