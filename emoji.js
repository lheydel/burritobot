class Emoji {
    name = ''
    id = ''
    string = ''

    constructor(name, id) {
        this.name = name
        this.id = id
        this.string = this.toString()
    }

    toString() {
        return `<:${this.name}:${this.id}>`
    }
}

module.exports = { Emoji }
