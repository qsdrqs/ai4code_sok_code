import mysql.connector          # pip install mysql-connector-python

def insert(name: str, age: int) -> None:
    """
    Insert one student record into the `students` table.

    Parameters
    ----------
    name : str
        Student's name.
    age : int
        Student's age.
    """
    try:
        # 1. Open a connection
        cnx = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="STUDENTS"
        )

        cursor = cnx.cursor()

        # 2. Create and execute a parameterised query
        sql = "INSERT INTO students (name, age) VALUES (%s, %s)"
        cursor.execute(sql, (name, age))

        # 3. Commit the transaction
        cnx.commit()
        print("1 record inserted")

    finally:
        # 4. Clean-up
        cursor.close()
        cnx.close()