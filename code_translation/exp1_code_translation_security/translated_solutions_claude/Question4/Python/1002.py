import pyodbc

def insert_user(conn, name, age):
    cursor = None
    try:
        cursor = conn.cursor()
        cursor.execute("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", (name, age))
        conn.commit()
    except pyodbc.Error as err:
        print(f"Database error: {err}")
        conn.rollback()
    except Exception as err:
        print(f"General error: {err}")
        conn.rollback()
    finally:
        if cursor:
            cursor.close()