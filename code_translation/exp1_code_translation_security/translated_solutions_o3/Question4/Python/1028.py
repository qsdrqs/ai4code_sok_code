def add_student(name, age):
    # Parameter placeholders (%s) are used so the driver takes care of escaping.
    sql = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"

    try:
        with connection.cursor() as cur:
            cur.execute(sql, (name, age))   # Bind the parameters safely
        connection.commit()
        print("1 record inserted")
    except Exception as err:
        connection.rollback()               # Optional but recommended
        raise err