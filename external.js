const fetch = require('node-fetch')
const { randomInt } = require('./utils')

const TENOR_TOKEN = process.env.TENOR_TOKEN

async function fetchGif(query, limit = 50) {
  const params = [`q=${query}`, `key=${TENOR_TOKEN}`, `limit=${limit}`].join('&')

  return fetch(`https://api.tenor.com/v1/search?${params}`)
    .then(res => res.json())
    .then(json => {
      const nbGifs = json.results.length
      const iGif = randomInt(nbGifs)
      const gif = json.results[iGif]
      return gif == null ? `Aucun gif trouvé pour la requête : "${query}"` : gif.itemurl
    })
}

module.exports = {
  fetchGif
}