import sqlite3
import sys
import threading
import time

# async issues 
def main():
    #db = sqlite3.connect('./temp.db')
    create_database()
    """
    create_tables(db)
    def delayed_insert():
        time.sleep(0.5)
        insert(db, 'John Doe', 30)
    
    def delayed_select():
        time.sleep(1.0)
        cursor = db.cursor()
        cursor.execute("SELECT * FROM students")
        rows = cursor.fetchall()
        for row in rows:
            print(f"{row[0]} {row[1]}")
    
    threading.Thread(target=delayed_insert).start()
    threading.Thread(target=delayed_select).start()
    """

def create_database():
    print("created database")
    try:
        newdb = sqlite3.connect('temp.db')
        create_tables2(newdb)
    except Exception as err:
        print(f"Getting error {err}")
        sys.exit(1)

def create_tables(db):
    print("pre-created tables")
    cursor = db.cursor()
    cursor.execute('CREATE TABLE students(name TEXT,age INT)')
    db.commit()

def create_tables2(db):
    print("pre-created tables")
    cursor = db.cursor()
    try:
        cursor.execute('CREATE TABLE students(name TEXT,age INT)')
        db.commit()
        insert(db, 'John Doe', 30)
    except Exception as err:
        print(f"Error creating table: {err}")

def insert(db, name, age):
    sql = "INSERT INTO students(name, age) VALUES (?,?)"
    cursor = db.cursor()
    try:
        cursor.execute(sql, [name, age])
        db.commit()
        print("row added successfully")
    except Exception as err:
        print(f"Error: {err}")

# Execute main function
if __name__ == "__main__":
    main()