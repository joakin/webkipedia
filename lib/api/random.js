import {get} from './fetch'
import {List} from 'immutable'
import array from 'array-range'

export default function (limit = 15) {
  // Get around api only allowing 10
  let requests = Math.ceil(limit / 10)
  return Promise.all(array(requests).map(getRandom))
    .then((results) => List(results).flatten(1))
    .catch(console.log.bind(console, 'ERROR'))
}

function getRandom () {
  return get({
    action: 'query',
    list: 'random',
    rnnamespace: '0',
    rnlimit: 10
  }).then(cleanResults)
}

function cleanResults (results) {
  return results.getIn(['query', 'random'], List())
}
