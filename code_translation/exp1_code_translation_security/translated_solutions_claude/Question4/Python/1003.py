import sqlite3

# db: sqlite3.Connection object, assumed to contain a STUDENTS (NAME, AGE) table
# name: student name string
# age: student age integer
#
# This function doesn't do any error checking on the types or sizes of `name`
# and `age`, and neither does Sqlite, so if you care, don't pass things that
# you don't want in your database.
def add_student(db, name, age):
    try:
        db.execute("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", (name, age))
        db.commit()
        print("inserted", None)
    except Exception as err:
        print("inserted", err)

# Export equivalent (if this were a module)
__all__ = ['add_student']