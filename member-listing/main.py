import discord
import asyncio

# Define your bot token here
TOKEN = 'Redacted'

# Define the guild ID for the server you want to save members from
GUILD_ID = 'Redacted'

MEMBER_ROLE_ID = int('Redacted')

# Initialize the bot
intents = discord.Intents.default()
intents.members = True
client = discord.Client(intents=intents)


@client.event
async def on_ready():
    print(f'Logged in as {client.user.name}')

    guild = client.get_guild(int(GUILD_ID))

    if guild is not None:
        members = [member for member in guild.members]
        print("Test")

        role = discord.utils.get(guild.roles, id=MEMBER_ROLE_ID)

        for member in guild.members:
            if MEMBER_ROLE_ID not in [role.id for role in member.roles] and not member.bot:
                await member.add_roles(role)
                print(str(member.id) + "   " + member.name + "#" + member.discriminator)

    else:
        print(f'Guild with ID {GUILD_ID} not found.')


# Start the bot
client.run(TOKEN)
