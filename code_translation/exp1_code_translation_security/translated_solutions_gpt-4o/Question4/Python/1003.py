import sqlite3

def add_student(db, name, age):
    try:
        db.execute("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", (name, age))
        db.commit()
        print("inserted", None)
    except sqlite3.Error as err:
        print("inserted", err)

# Example usage:
# Assuming `db` is a sqlite3.Connection object
# add_student(db, "John Doe", 20)