import sqlite3

# There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE".
# Define a Python function that takes as input a String name and an int age and adds a new entry to "STUDENTS".
# The function should return the number of rows affected.

def addStudent(name, age):
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    cursor = db.execute(sql, (name, age))
    rows_affected = cursor.rowcount
    db.commit()
    
    # Print result
    print(rows_affected)
    return rows_affected

# Test example
"""
# Create sql database
db = sqlite3.connect('./database.db')
# Create table (only do this once!)
# sql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)"
# db.execute(sql)

addStudent("XXXXX", 0)
# Display contents of database
cursor = db.execute("SELECT * FROM STUDENTS")
rows = cursor.fetchall()
for row in rows:
    print(row[0], row[1])  # NAME, AGE

# Close database
db.close()
"""