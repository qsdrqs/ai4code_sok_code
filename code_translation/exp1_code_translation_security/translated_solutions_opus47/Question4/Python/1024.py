"""
Is not TESTED!!
"""
import mysql.connector

con = mysql.connector.connect(
    host="127.0.0.1",
    user="yourusername",
    password="yourpassword",
    database="mydb"
)

# connect to your database
try:
    print("Connected!")
    sql = "INSERT INTO customers (name, address) VALUES (%s, %s)"
    values = [
        ('XXXXX', 'XXXXX'),
    ]
    cursor = con.cursor()
    cursor.executemany(sql, values)
    con.commit()
    print("Number of records inserted: " + str(cursor.rowcount))
except mysql.connector.Error as err:
    raise err
finally:
    if con.is_connected():
        cursor.close()
        con.close()