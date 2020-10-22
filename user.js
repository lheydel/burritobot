const { CLOCHETTE, BRINDILLE, BOB, BURRITAL } = require("./emoji")

class User {
    id = ''
    string = ''
    gifReaction // Emoji

    constructor(id, gifReaction) {
        this.id = id
        this.string = this.toString()
        this.gifReaction = gifReaction
    }

    toString() {
        return `<@!${this.id}>`
    }
}

const HYRLOS = new User('168333966716174337', CLOCHETTE)
const LOUEC = new User('160483264761430016', BRINDILLE)
const MROHMS = new User('366581141118910464', BOB)
const BOT = new User('726715798676176947', BURRITAL)

function getUserById(id) {
    return [HYRLOS, LOUEC, MROHMS, BOT].find(user => user.id === id)
}

module.exports = { 
    HYRLOS,
    LOUEC,
    MROHMS,
    BOT,

    getUserById
}
