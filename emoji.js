class Emoji {
    name = ''
    id = ''

    constructor(name, id) {
        this.name = name
        this.id = id
    }

    toString() {
        return `<:${name}:${id}>`
    }
}

module.exports = { Emoji }
