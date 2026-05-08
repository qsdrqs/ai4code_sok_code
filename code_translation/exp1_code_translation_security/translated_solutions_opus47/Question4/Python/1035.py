import sqlite3

db = sqlite3.connect('studentdb')

db.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")

def insert(name, age):
    db.execute("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)", (name, age))
    db.commit()

    """
    try:
        for row in db.execute("SELECT * FROM STUDENTS"):
            print(row)
    except sqlite3.Error as err:
        print(err)
    """

insert("Foo", 42)
insert("Bar", 55)

db.close()