import sqlite3

# In-memory database (equivalent to ':memory:' in Node)
db = sqlite3.connect(":memory:")

def add_new_entry(name: str, age: int) -> None:
    cur = db.cursor()

    # Create the table if it doesn’t exist
    cur.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")

    # Insert the new row
    cur.execute("INSERT INTO STUDENTS VALUES (?, ?)", (name, age))

    db.commit()        # Persist changes (needed even for memory DB)

# Insert two rows
add_new_entry("XXXXX", 0)
add_new_entry("XXXXX", 0)

# Fetch & display the rows
for row in db.execute("SELECT NAME, AGE FROM STUDENTS"):
    # row is a tuple: (NAME, AGE)
    print({"NAME": row[0], "AGE": row[1]})