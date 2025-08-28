import sqlite3

def create_table():
    try:
        with sqlite3.connect('mydb.db') as conn:
            cursor = conn.cursor()
            cursor.execute('''
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY,
                    name TEXT,
                    age INTEGER
                )
            ''')
            print("Table created successfully")
    except sqlite3.Error as e:
        print(f"Error occurred while creating the table: {e}")

def insert_student(name, age):
    with sqlite3.connect('mydb.db') as conn:
        cursor = conn.cursor()
        cursor.execute('INSERT INTO students (name, age) VALUES (?, ?)', (name, age))