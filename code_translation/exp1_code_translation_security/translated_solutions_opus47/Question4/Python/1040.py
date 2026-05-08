import sqlite3

db = sqlite3.connect(':memory:')


def add_new_entry(name, age):
    cursor = db.cursor()
    cursor.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)")
    cursor.execute("INSERT INTO STUDENTS VALUES (?, ?)", (name, age))
    db.commit()


add_new_entry("XXXXX", 0)
add_new_entry("XXXXX", 0)

cursor = db.cursor()
for row in cursor.execute("SELECT NAME, AGE FROM STUDENTS"):
    print(row)

db.close()