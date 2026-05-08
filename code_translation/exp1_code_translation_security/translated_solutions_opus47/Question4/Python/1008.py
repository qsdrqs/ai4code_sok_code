import sqlite3

# Python function to create SQL table "students" with 'name' and 'age'
def create_table():
    db = None
    try:
        db = sqlite3.connect('mydb.db')
        cursor = db.cursor()
        cursor.execute(
            "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)"
        )
        db.commit()
        print("Table created successfully")
    except sqlite3.Error:
        print("Error occurred while creating the table.")
    finally:
        if db:
            db.close()


# Python function to insert string name and an int age to 'students'
def insert_student(name, age):
    db = sqlite3.connect('mydb.db')
    cursor = db.cursor()
    cursor.execute('INSERT INTO students (name, age) VALUES (?, ?)', (name, age))
    db.commit()
    db.close()