const Discord = require('discord.js')
const { Emoji } = require('./emoji')

const BURRITAL = new Emoji('burrital', '369550219173429259')
const BURRITOEL = new Emoji('burritoel', '526907423160533012')

const OUI = new Emoji('oui', '593794204598272001')
const NON = new Emoji('non', '595161673438855168')
const MAYBE = new Emoji('maybe', '596709528280629267')

const OK1 = new Emoji('OK1', '369541026404237312')
const OK2 = new Emoji('OK2', '655871904548126741')

const PD = new Emoji('PD', '513834875418312723')
const SAD = new Emoji('sad', '369883690479648768')

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
      channel.send('Commands: ' + ['blblbl', 'ok', 'pd', 'noise'].join(', '))
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
      break;
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
  }
})