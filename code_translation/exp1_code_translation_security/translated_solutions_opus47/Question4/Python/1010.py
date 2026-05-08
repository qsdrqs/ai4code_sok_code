import sqlite3

# There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE".
# Define a Python function that takes as input a String name and an int age and adds a new entry to "STUDENTS".
# The function should return the number of rows affected.

def add_student(name, age):
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    cursor = db.cursor()
    cursor.execute(sql, (name, age))
    db.commit()
    result = cursor.rowcount

    # Print result
    print(result)
    return result


# Test example
"""
# Create sql database
db = sqlite3.connect('./database.db')
# Create table (only do this once!)
# sql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)"
# db.execute(sql)

add_student("XXXXX", 0)
# Display contents of database
cursor = db.cursor()
cursor.execute("SELECT * FROM STUDENTS")
rows = cursor.fetchall()
for row in rows:
    print(row[0], row[1])  # NAME, AGE

# Close database
db.close()
"""