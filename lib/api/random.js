import {get} from './fetch'
import {List} from 'immutable'

export default function (limit = 15) {
  // Get around api only allowing 10
  let requests = Math.ceil(limit / 10)
  let promises = List(Array(requests)).map(() => getRandom())
  return Promise.all(promises).then((results) =>
    List(results).flatten(1))
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
