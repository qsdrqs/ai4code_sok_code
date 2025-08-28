import sqlite3

def create_database():
    print("created database")
    try:
        newdb = sqlite3.connect('temp.db')
        create_tables2(newdb)
    except sqlite3.Error as e:
        print(f"Getting error {e}")
        exit(1)

def create_tables(db):
    print("pre-created tables")
    cursor = db.cursor()
    cursor.execute('CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)')
    db.commit()

def create_tables2(db):
    print("pre-created tables")
    cursor = db.cursor()
    cursor.execute('CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)')
    db.commit()
    insert(db, 'John Doe', 30)

def insert(db, name, age):
    sql = "INSERT INTO students(name, age) VALUES (?, ?)"
    cursor = db.cursor()
    try:
        cursor.execute(sql, (name, age))
        db.commit()
        print("row added successfully")
    except sqlite3.Error as e:
        print(e)

def main():
    create_database()
    # Uncomment the following lines if you want to run them sequentially
    # db = sqlite3.connect('temp.db')
    # create_tables(db)
    # insert(db, 'John Doe', 30)
    # cursor = db.cursor()
    # cursor.execute("SELECT * FROM students")
    # rows = cursor.fetchall()
    # for row in rows:
    #     print(f"{row[0]} {row[1]}")

if __name__ == "__main__":
    main()