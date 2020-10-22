const insult = require('insult')
const insults = require('insults')
const insultCompliment = require('insult-compliment')
const { randomInt } = require('./utils')

class Insulter {
    getInsult = () => ""
    chanceToGet = 0

    constructor(chanceToGet, getInsult) {
        this.chanceToGet = chanceToGet
        this.getInsult = getInsult
    }
}

const INSULTERS = [
    new Insulter(5, () => insult.Insult()),
    new Insulter(50, () => insults.default()),
    new Insulter(45, () => randomInt(100) < 5 ? insultCompliment.Compliment() : insultCompliment.Insult())
]

function selectInsult() {
    const insulter = chooseInsulter()
    return insulter.getInsult()
}

function chooseInsulter() {
    const totalChances = INSULTERS.reduce((acc, insulter) => acc + insulter.chanceToGet, 0)
    const random = randomInt(totalChances)
    let chanceAcc = 0
    for (let i = 0; i < INSULTERS.length; i++) {
        chanceAcc += INSULTERS[i].chanceToGet
        if (random < chanceAcc) {
            return INSULTERS[i]
        }
    }

}

module.exports = {
    selectInsult
}
