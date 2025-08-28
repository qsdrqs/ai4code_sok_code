import asyncio
import aiohttp

async def sql_insert(name, age):
    url = f"sqlapi.php?action=INSERTINTOSTUDENTS&&name={name}&&age={age}"
    async with aiohttp.ClientSession() as session:
        async with session.get(url) as response:
            if response.status == 200:
                return await response.text()
            else:
                raise Exception(f"HTTP error {response.status}")

async def main():
    try:
        result = await sql_insert("XXXXX", 0)
        print("result", result)
    except Exception as e:
        print("error", e)

if __name__ == "__main__":
    asyncio.run(main())