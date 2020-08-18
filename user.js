const { CLOCHETTE, BRINDILLE, BOB } = require("./emoji")

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

function getUserById(id) {
    return [HYRLOS, LOUEC, MROHMS].find(user => user.id === id)
}

module.exports = { 
    HYRLOS,
    LOUEC,
    MROHMS,

    getUserById
}
