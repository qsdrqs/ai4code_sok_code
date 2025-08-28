def add_new_entry(name, age):
    import sqlite3

    conn = sqlite3.connect('database.db')
    cur = conn.cursor()

    cur.execute("INSERT INTO STUDENTS VALUES (?, ?)", (name, age))

    cur.execute("SELECT rowid AS id, NAME, AGE FROM STUDENTS")
    rows = cur.fetchall()
    for row in rows:
        print(f"{row[0]}: {row[1]} {row[2]}")

    conn.close()

add_new_entry("XXXXX", 0)