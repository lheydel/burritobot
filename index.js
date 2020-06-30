const Discord = require('discord.js')

const OUI = '<:oui:593794204598272001>'
const NON = '<:non:595161673438855168>'
const MAYBE = '<:maybe:596709528280629267>'

const OK = '<:OK:369541026404237312>'
const Ok = '<:Ok:655871904548126741>'

const PD = '<:PD:513834875418312723>'
const SAD = '<:sad:369883690479648768>'

const bot = new Discord.Client()
bot.login(process.env.BOT_TOKEN)

bot.on('ready', () => {
  console.log('Connected')
})

bot.on('message', async (message) => {
  const content = message.content
  const channel = message.channel

  if (content[0] !== '!') {
    if (content.endsWith('?') && Math.random() <= 0.05) {
      const rdm = Math.random()
      rdm <= 0.495 ? channel.send(OUI)
        : rdm <= 0.99 ? channel.send(NON)
        : channel.send(MAYBE)
    }

    if (Math.random() <= 0.002) {
      channel.send(Math.random <= 0.5 ? OK : Ok)
    }

    return
  }

  const [cmd, ...textArray] = content.substring(1).split(' ')
  const text = textArray.join(' ')
  
  switch(cmd) {
    case 'cmd':
      channel.send('Commands: ' + ['blblbl', 'ok', 'pd', 'noise'].join(', '))
      break
    case 'blblbl':
      message.delete()
      channel.send('I\'m a burritooooo!')
      break
    case 'ok': 
      message.delete()
      channel.send(OK)
      break
    case 'pd':
      message.delete()
      channel.send(PD)
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
      msg.delete()
  }
})