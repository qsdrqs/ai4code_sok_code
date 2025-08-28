import mysql.connector

def add_student(name, age):
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    print(sql)
    return sql

# Establish a connection to the MySQL database
con = mysql.connector.connect(
    host="localhost",
    user="yourusername",
    password="yourpassword"
)

# Create a cursor object using the connection
cursor = con.cursor()

try:
    # Execute the SQL query returned by add_student function
    cursor.execute(add_student("XXXXX", 0))
    # Commit the transaction
    con.commit()
    print("Result: ", cursor.rowcount, "record inserted.")
except mysql.connector.Error as err:
    print("Error: ", err)
finally:
    # Close the cursor and connection
    cursor.close()
    con.close()