import sqlite3

def addSQLentry(name, age):
    sql = "INSERT INTO students (name, age) VALUES (?, ?)"
    params = [name, age]
    
    try:
        cursor = db.cursor()
        cursor.execute(sql, params)
        db.commit()
        print(f"User added with id: {cursor.lastrowid}")
    except Exception as err:
        print(err)