const Discord = require('discord.js')

const OUI = '593794204598272001'
const NON = '595161673438855168'
const MAYBE = '596709528280629267'

const bot = new Discord.Client()
bot.login(process.env.BOT_TOKEN)

bot.on('ready', () => {
  console.log('Connected')
})

bot.on('message', async (message) => {
  const content = message.content
  const channel = message.channel

  if (content.endsWith('?') && Math.random() <= 0.1) {
    const rdm = Math.random()
    rdm <= 0.495 ? message.react(OUI)
      : rdm <= 0.99 ? message.react(NON)
      : message.react(MAYBE)
  }

  // if (content.includes('alors')) {
  //   channel.send('<:sad:369883690479648768>')
  // }

  if (content[0] !== '!') {
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
      channel.send('<:OK:369541026404237312>')
      break
    case 'pd':
      message.delete()
      channel.send('<:PD:513834875418312723>')
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