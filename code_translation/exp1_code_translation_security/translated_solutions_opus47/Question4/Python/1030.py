import sqlite3
import sys


def main():
    # async issues
    # db = sqlite3.connect('./temp.db')
    create_database()
    '''
    create_tables(db)
    time.sleep(0.5)
    insert(db, 'John Doe', 30)
    time.sleep(1.0)
    db.row_factory = sqlite3.Row
    rows = db.execute("select * FROM students").fetchall()
    for row in rows:
        print(row['name'] + " " + str(row['age']))
    '''


def create_database():
    print("created database")
    try:
        newdb = sqlite3.connect('temp.db')
        create_tables2(newdb)
    except sqlite3.Error as err:
        print("Getting error " + str(err))
        sys.exit(1)


def create_tables(db):
    print("pre-created tables")
    db.execute('CREATE TABLE students(name TEXT,age INT)')


def create_tables2(db):
    print("pre-created tables")
    try:
        db.execute('CREATE TABLE students(name TEXT,age INT)')
    except sqlite3.Error:
        pass  # matches JS callback which ignores err
    insert(db, 'John Doe', 30)


def insert(db, name, age):
    sql = "insert into students(name, age) VALUES (?,?)"
    try:
        db.execute(sql, (name, age))
        db.commit()
        print("row added successfully")
    except sqlite3.Error as err:
        print(str(err))


if __name__ == "__main__":
    main()