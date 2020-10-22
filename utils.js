function isEmptyString(str) {
    return typeof str === 'string' 
        && (str == null || str.trim().length === 0)
}

function randomInt(max) {
    return Math.ceil(Math.random() * max - 1)
}

module.exports = {
    isEmptyString,
    randomInt
}