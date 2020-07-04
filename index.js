const Discord = require('discord.js')
const { fetchGif } = require('./external')
const { isEmptyString } = require('./utils')

const { BURRITAL, BURRITOEL, OUI, NON, MAYBE, OK1, OK2, PD, SAD } = require('./emoji')

const bot = new Discord.Client()
bot.login(process.env.BOT_TOKEN)

bot.on('ready', () => {
  console.log('Connected')
})

bot.on('message', async (message) => {
  const content = message.content.toLowerCase()
  const channel = message.channel

  if (content[0] !== '!') {
    if (content.endsWith('?') && Math.random() <= 0.05) {
      const rdm = Math.random()
      rdm <= 0.495 ? channel.send(OUI.string)
        : rdm <= 0.99 ? channel.send(NON.string)
        : channel.send(MAYBE.string)
    }

    if (Math.random() <= 0.002) {
      channel.send(Math.random <= 0.5 ? OK1.string : OK2.string)
    }

    if (content.includes('burrito')) {
      message.react(BURRITAL.id)
    }

    if (content.includes('noel')) {
      message.react(BURRITOEL.id)
    }

    if (content.includes('itk')) {
      channel.send(await fetchGif('cow'))
    }

    if (['bot', 'pue'].every(s => content.includes(s))) {
      message.react(SAD.id)
    }

    return
  }

  const [cmd, ...textArray] = content.substring(1).split(' ')
  const text = textArray.filter(s => s != '-v').join(' ')
  const verbose = textArray.includes('-v')
  
  switch(cmd) {
    case 'cmd':
      message.delete()
      channel.send('Commands: ' + ['blblbl', 'ok', 'pd', 'noise', 'gif'].join(', '))
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
      channel.send(await fetchGif(query))
      break
  }
})