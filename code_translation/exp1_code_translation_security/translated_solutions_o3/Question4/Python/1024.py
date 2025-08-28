"""
NOT TESTED!!
"""
import mysql.connector
from mysql.connector import Error

try:
    # create a connection to your database
    con = mysql.connector.connect(
        host="127.0.0.1",
        user="yourusername",
        password="yourpassword",
        database="mydb"
    )

    if con.is_connected():
        print("Connected!")

        sql = "INSERT INTO customers (name, address) VALUES (%s, %s)"
        values = [
            ("XXXXX", "XXXXX"),
        ]

        cursor = con.cursor()
        cursor.executemany(sql, values)     # insert all rows in one call
        con.commit()                        # commit the transaction

        print("Number of records inserted:", cursor.rowcount)

except Error as err:
    raise err      # re-raise so the caller sees the failure (same effect as `throw` in JS)

finally:
    if 'cursor' in locals():
        cursor.close()
    if 'con' in locals() and con.is_connected():
        con.close()