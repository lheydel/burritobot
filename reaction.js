const { BURRITAL, BURRITOEL } = require("./emoji")
const { fetchGif } = require("./external")

TYPE_REACT = 'react'
TYPE_WRITE = 'write'
// TYPE_RESPOND = 'respond'

class Reaction {
    pattern = ''
    type = TYPE_WRITE
    responseGetter = async () => ''

    constructor(pattern, type, responseGetter) {
        this.pattern = pattern
        this.responseGetter = responseGetter
        this.type = type
    }

    async sendResponse(message) {
      const channel = message.channel
      const response = await this.responseGetter()

      switch (this.type) {
        case TYPE_WRITE:
          channel.send(response)
          break;
        case TYPE_REACT: {
          message.react(response)
          break;
        }
      }
    }
}

const REACTIONS = [
    new Reaction('burrito', TYPE_REACT, () => BURRITAL.id),
    new Reaction('noel', TYPE_REACT, () => BURRITOEL.id),
    new Reaction('itk', TYPE_WRITE, () => fetchGif('cow'))
]

function react(message) {
    const content = message.content.toLowerCase()

    REACTIONS.filter(reaction => content.includes(reaction.pattern))
      .forEach(reaction => reaction.sendResponse(message))
}

module.exports = { react }
