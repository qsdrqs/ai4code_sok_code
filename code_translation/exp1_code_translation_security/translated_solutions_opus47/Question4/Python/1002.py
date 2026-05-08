import pyodbc

# assume active conn
def insert_user(conn, name, age):
    try:
        cursor = conn.cursor()
        cursor.execute(
            "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);",
            name, age
        )
        conn.commit()
    except pyodbc.Error as err:
        print(err)