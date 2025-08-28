import sqlite3

# Establish a connection to the SQLite database (or create it if it doesn't exist)
db = sqlite3.connect('students.db')

# Create the 'students' table if it doesn't already exist
with db:
    db.execute('''
        CREATE TABLE IF NOT EXISTS students (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            age INTEGER
        )
    ''')

def addSQLentry(name, age):
    """
    Inserts a new student into the 'students' table and prints the last inserted ID.
    
    Parameters:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    sql = "INSERT INTO students (name, age) VALUES (?, ?)"
    params = (name, age)

    cursor = db.cursor()
    try:
        cursor.execute(sql, params)
        db.commit()
    except sqlite3.Error as e:
        print(f"Error: {e}")
    finally:
        # Always print the last inserted row ID, even if an error occurred
        print(f"User added with id: {cursor.lastrowid}")
        cursor.close()