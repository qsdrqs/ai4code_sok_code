import sqlite3


def add_new_entry(name, age):
    db = sqlite3.connect('database.db')
    cursor = db.cursor()

    cursor.execute("INSERT INTO STUDENTS VALUES (?,?)", (name, age))
    db.commit()

    for row in cursor.execute("SELECT rowid AS id, NAME, AGE FROM STUDENTS"):
        print(f"{row[0]}: {row[1]} {row[2]}")

    db.close()


add_new_entry("XXXXX", 0)