import mysql.connector                # or any other MySQL-Python driver that will be supplied

def insert_student(name, age):
    # Open a connection
    cnx = mysql.connector.connect(**config)   # `config` is the same dict you used in JS
    cursor = cnx.cursor()
    
    # INSERT statement
    sql = (
        "INSERT INTO STUDENTS (title, completed) "
        "VALUES ('Learn how to insert a new row', TRUE)"
    )
    
    # Execute the INSERT
    cursor.execute(sql)
    cnx.commit()        # make sure the change is saved
    
    # Clean-up
    cursor.close()
    cnx.close()

insert_student("XXXXX", 0)