def add_sql_entry(name, age):
    sql = "INSERT INTO students (name, age) VALUES (?, ?)"
    params = (name, age)

    try:
        cursor = db.cursor()          # `db` is assumed to be an open database connection
        cursor.execute(sql, params)   # execute the INSERT statement
        db.commit()                   # persist the change
        print(f"User added with id: {cursor.lastrowid}")
    except Exception as err:
        print(err)