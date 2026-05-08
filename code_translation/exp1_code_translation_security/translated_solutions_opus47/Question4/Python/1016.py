import sqlite3  # or psycopg2, mysql.connector, etc.

def add_student(name, age):
    conn = sqlite3.connect("students.db")
    try:
        cursor = conn.cursor()
        cursor.execute(
            "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
            (name, age)  # parameters are safely escaped by the driver
        )
        conn.commit()
    finally:
        conn.close()