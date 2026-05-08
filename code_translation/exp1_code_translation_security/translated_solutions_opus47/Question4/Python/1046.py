# import mysql.connector
# import config
# create config and connection to your respective database then use the function
# connection = mysql.connector.connect(**config.config)

def insert_into(name, age):
    sql = f"""INSERT INTO STUDENTS(NAME,AGE)
                VALUES ({name}, {age})"""
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    connection.close()