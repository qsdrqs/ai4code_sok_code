import asyncio
import aiohttp

async def sql_insert(name, age):
    """
    Makes a GET request to the sqlapi.php to insert data into the students table.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.

    Returns:
        str: The response from the server.
    """
    url = "sqlapi.php"
    params = {
        "action": "INSERTINTOSTUDENTS",
        "name": name,
        "age": age
    }

    try:
        async with aiohttp.ClientSession() as session:
            async with session.get(url, params=params) as response:
                response.raise_for_status()  # Raise an exception for bad status codes
                return await response.text()
    except aiohttp.ClientError as e:
        return str(e)

async def main():
    name = "XXXXX"
    age = 0

    try:
        result = await sql_insert(name, age)
        print("result", result)
    except Exception as e:
        print("error", str(e))

if __name__ == "__main__":
    asyncio.run(main())