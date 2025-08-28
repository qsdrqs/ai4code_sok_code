import mysql.connector
# import config  # Uncomment and configure as needed

# Create config and connection to your respective database then use the function
# Example config:
# config = {
#     'host': 'localhost',
#     'user': 'your_username',
#     'password': 'your_password',
#     'database': 'your_database'
# }
# connection = mysql.connector.connect(**config)

def insert_into(name, age):
    # Note: Using parameterized query to prevent SQL injection
    sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (%s, %s)"
    
    cursor = connection.cursor()
    cursor.execute(sql, (name, age))
    connection.commit()  # Commit the transaction
    cursor.close()
    connection.close()