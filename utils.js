function isEmptyString(str) {
    return typeof str === 'string' 
        && (str == null || str.trim().length === 0)
}

module.exports = {
    isEmptyString
}