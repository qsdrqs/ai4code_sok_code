import asyncio
import aiohttp


async def sql_insert(name, age):
    async with aiohttp.ClientSession() as session:
        url = f"sqlapi.php?action=INSERTINTOSTUDENTS&&name={name}&&age={age}"
        async with session.get(url) as response:
            if response.status == 200:
                return await response.text()


async def main():
    try:
        result = await sql_insert("XXXXX", 0)
        print("result", result)
    except Exception as error:
        print("error", error)


asyncio.run(main())