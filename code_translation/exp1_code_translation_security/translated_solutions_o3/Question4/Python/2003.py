import sqlite3


# ------------------------------------------------------------------
# 1. Create an in-memory database connection
# ------------------------------------------------------------------
conn = sqlite3.connect(":memory:")
cursor = conn.cursor()

# Create the table
cursor.execute("CREATE TABLE students (name TEXT, age INT)")
conn.commit()          # Commit DDL changes


# ------------------------------------------------------------------
# 2. Data-insertion helper (Pythonic equivalent of the JS function)
# ------------------------------------------------------------------
def insert_student(connection, name, age):
    """
    Insert one student record into the `students` table.
    Uses parameterized SQL to avoid SQL-injection issues.
    """
    with connection:                      # `with` auto-commits/rolls back
        connection.execute(
            "INSERT INTO students (name, age) VALUES (?, ?)",
            (name, age)
        )


# ------------------------------------------------------------------
# 3. Insert sample data
# ------------------------------------------------------------------
insert_student(conn, "XXXXX", 0)


# ------------------------------------------------------------------
# 4. Read & dump the table (equivalent to db.each in Node)
# ------------------------------------------------------------------
for row in conn.execute("SELECT * FROM students"):
    # Row is a tuple: (name, age)
    print(row)


# ------------------------------------------------------------------
# 5. Close the connection
# ------------------------------------------------------------------
conn.close()