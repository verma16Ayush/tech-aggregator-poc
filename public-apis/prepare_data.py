import os
from dotenv import load_dotenv
import mysql.connector
from mysql.connector import Error

def create_connection(host, user, password, database):
    """Create a connection to the MySQL database"""
    try:
        connection = mysql.connector.connect(
            host=host,
            user=user,
            password=password,
            database=database,
            auth_plugin='mysql_native_password'
        )
        print("Connected to MySQL database")
        return connection
    except Error as e:
        print(f"Error connecting to MySQL database: {e}")
        return None

def table_exists(connection, table_name):
    """Check if a table exists in the database"""
    cursor = connection.cursor()
    cursor.execute(f"SHOW TABLES LIKE '{table_name}'")
    result = cursor.fetchone()
    cursor.close()
    return result is not None

def create_table_from_file(connection, sql_file):
    """Execute SQL queries from a file to create and insert data into a table"""
    cursor = connection.cursor()
    try:
        with open(sql_file, 'r') as file:
            queries = file.read().split(';')
            for query in queries:
                if query.strip():
                    cursor.execute(query)
        connection.commit()
        print("Table created and data inserted successfully")
    except Error as e:
        print(f"Error creating table: {e}")
        connection.rollback()
    finally:
        cursor.close()

def main():
    load_dotenv()

    host = os.getenv("DB_HOST")
    user = os.getenv("DB_USER")
    password = os.getenv("DB_PASSWORD")
    database = os.getenv("DB_DATABASE")
    table_name = os.getenv("TABLE_NAME")
    sql_file = 'test-data.sql'  

    connection = create_connection(host, user, password, database)
    if connection is None:
        return

    if not table_exists(connection, table_name):
        create_table_from_file(connection, sql_file)
    else:
        print("Table already exists")

    connection.close()
    print("Connection closed")

if __name__ == "__main__":
    main()