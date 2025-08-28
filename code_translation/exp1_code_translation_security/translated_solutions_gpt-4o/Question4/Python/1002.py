import pyodbc

def insert_user(conn, name, age):
    try:
        cursor = conn.cursor()
        query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);"
        cursor.execute(query, (name, age))
        conn.commit()
    except Exception as e:
        print(e)
    finally:
        cursor.close()