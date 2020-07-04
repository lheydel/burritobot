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

const BURRITAL = new Emoji('burrital', '369550219173429259')
const BURRITOEL = new Emoji('burritoel', '526907423160533012')

const OUI = new Emoji('oui', '593794204598272001')
const NON = new Emoji('non', '595161673438855168')
const MAYBE = new Emoji('maybe', '596709528280629267')

const OK1 = new Emoji('OK1', '369541026404237312')
const OK2 = new Emoji('OK2', '655871904548126741')

const PD = new Emoji('PD', '513834875418312723')
const SAD = new Emoji('sad', '369883690479648768')

module.exports = { 
    BURRITAL, 
    BURRITOEL, 

    OUI, 
    NON, 
    MAYBE, 

    OK1, 
    OK2, 
    
    PD, 
    SAD
 }
