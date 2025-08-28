import mysql.connector   # `mysql-connector-python` will be available

def insert() -> None:
    # establish the connection
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword",
        database="mydb"
    )

    cursor = con.cursor()
    print("Connected!")

    sql = "INSERT INTO students (name, age) VALUES (%s, %s)"

    # prompt user for data
    name = input("enter a name: ")

    # ensure we get a valid integer for age
    while True:
        try:
            age = int(input("enter an age: "))
            break
        except ValueError:
            print("Please enter a valid integer.")

    # execute the INSERT
    cursor.execute(sql, (name, age))
    con.commit()                              # make the change permanent

    print(f"Number of records inserted: {cursor.rowcount}")

    # clean-up
    cursor.close()
    con.close()


if __name__ == "__main__":
    insert()