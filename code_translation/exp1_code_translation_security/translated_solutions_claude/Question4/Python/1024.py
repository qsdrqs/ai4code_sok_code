"""
Is not TESTED!!
"""
import mysql.connector
from mysql.connector import Error

# Database connection configuration
config = {
    'host': '127.0.0.1',
    'user': 'yourusername',
    'password': 'yourpassword',
    'database': 'mydb'
}

try:
    # Connect to your database
    con = mysql.connector.connect(**config)
    
    if con.is_connected():
        print("Connected!")
        
        cursor = con.cursor()
        sql = "INSERT INTO customers (name, address) VALUES (%s, %s)"
        values = [
            ('XXXXX', 'XXXXX'),
        ]
        
        cursor.executemany(sql, values)
        con.commit()
        
        print(f"Number of records inserted: {cursor.rowcount}")
        
except Error as err:
    raise err
    
finally:
    # Close database connection
    if con.is_connected():
        cursor.close()
        con.close()