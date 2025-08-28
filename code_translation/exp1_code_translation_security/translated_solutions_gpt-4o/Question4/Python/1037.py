import mysql.connector

def insert_student(name, age):
    # Create a connection to the database
    connection = mysql.connector.connect(**config)
    
    # Insert statement
    sql = "INSERT INTO STUDENTS(title, completed) VALUES(%s, %s)"
    values = ('Learn how to insert a new row', True)
    
    # Execute the insert statement
    cursor = connection.cursor()
    cursor.execute(sql, values)
    
    # Commit the transaction
    connection.commit()
    
    # Close the connection
    cursor.close()
    connection.close()

# Call the function
insert_student("XXXXX", 0)