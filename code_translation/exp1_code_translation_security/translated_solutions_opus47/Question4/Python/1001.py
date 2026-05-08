import sqlite3

def add_sql_entry(name, age):
    sql = "INSERT INTO students (name, age) VALUES (?, ?)"
    params = (name, age)
    try:
        cursor = db.cursor()
        cursor.execute(sql, params)
        db.commit()
        print(f"User added with id: {cursor.lastrowid}")
    except sqlite3.Error as err:
        print(err)