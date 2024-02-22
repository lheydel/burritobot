const Discord = require('discord.js')
const { fetchGif } = require('./external')
const { isEmptyString } = require('./utils')
const { OUI, NON, MAYBE, OK1, OK2, PD, SAD, SIXSIXSIX, NINENINENINE, CHECK} = require('./emoji')
const { getUserById, isBot } = require('./user')
const { react } = require('./reaction')
const { selectInsult, selectCompliment } = require('./insult')
const { remove: unaccent } = require('diacritics')

require('dotenv').config()

const bot = new Discord.Client()
bot.login(process.env.BOT_TOKEN)

bot.on('ready', () => {
  console.log('Connected')
})

bot.on('message', async (message) => {
  const contentNormalized = unaccent(message.content.toLowerCase().trim())
  const channel = message.channel
  const author = message.author

  if (contentNormalized[0] !== '!') {
    if (contentNormalized.endsWith('?') && Math.random() <= 0.01) {
      const rdm = Math.random()
      rdm <= 0.495 ? channel.send(OUI.string)
        : rdm <= 0.99 ? channel.send(NON.string)
        : channel.send(MAYBE.string)
    }

    if (Math.random() <= 0.002) {
      channel.send(Math.random() <= 0.5 ? OK1.string : OK2.string)
    }

    react(message)

    if (['bot', 'pue'].every(s => contentNormalized.includes(s))) {
      message.react(SAD.id)
    }

    if (contentNormalized.includes(SIXSIXSIX.string)) {
      message.react(NINENINENINE.id)
    }

    if (contentNormalized.includes(NINENINENINE.string) || contentNormalized.includes("666")) {
      message.react(SIXSIXSIX.id)
    }

    if (contentNormalized == "k") {
      message.delete()
      const checkMsg = await channel.send(CHECK.string)
      sign(checkMsg, author)
    }
    
    return
  }

  const [cmd, ...textArray] = contentNormalized.substring(1).split(' ')
  const text = textArray.filter(s => s != '-v').join(' ')
  const verbose = textArray.includes('-v')
  
  switch(cmd) {
    case 'cmd':
      message.delete()
      channel.send('Commands: ' + ['blblbl', 'ok', 'pd', 'noise', 'gif', 'insult'].join(', '))
      break
    case 'blblbl':
      message.delete()
      channel.send('I\'m a burritooooo!')
      break
    case 'ok': 
      message.delete()
      channel.send(OK1.string)
      break
    case 'pd':
      message.delete()
      channel.send(PD.string)
      break
    // case 'spam':
    //   await new Promise(r => setTimeout(r, (Math.floor(Math.random() * 15) + 30) * 1000));
    //   channel.send('!spam')
    //   await new Promise(r => setTimeout(r, (Math.floor(Math.random() * 15) + 30) * 1000));
    //   channel.send('!spam')
    //   break;
    case 'noise': 
      message.delete()
      const msg = await channel.send(text, { tts: true })
      if (!verbose) {
        msg.delete()
      }
      break
    case 'gif':
      message.delete()
      const query = isEmptyString(text) ? 'burrito' : text
      const gifMsg = await channel.send(await fetchGif(query))
      sign(gifMsg, author)
      break
    case 'insult':
      message.delete()
      const target = message.content.substring(message.content.indexOf(' ')).trim()
      const insult = isBot(target) ? selectCompliment() : selectInsult()
      const insultToSend = insult.substring(0, 1).toLowerCase() + insult.substring(1)
      const insultMsg = await channel.send(`${target}, ${insultToSend}`)
      sign(insultMsg, author)
  }
})

function sign(message, author) {
  message.react(getUserById(author.id).gifReaction.id)
}
