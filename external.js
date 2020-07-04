const fetch = require('node-fetch')

const TENOR_TOKEN = process.env.TENOR_TOKEN

async function fetchGif(query) {
    const params = [`q=${query}`, `key=${TENOR_TOKEN}`, 'limit=50'].join('&')

    return fetch(`https://api.tenor.com/v1/search?${params}`)
      .then(res => res.json())
      .then(json => {
        const nbGifs = json.results.length
        const iGif = Math.ceil(Math.random() * nbGifs)
        return json.results[iGif].itemurl
      })
}

module.exports = {
    fetchGif
}