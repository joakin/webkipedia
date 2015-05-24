import {get} from './fetch'
import {List, Map} from 'immutable'
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
    prop: ['extracts', 'pageimages'].join('|'),
    piprop: ['thumbnail', 'name', 'original'].join('|'),
    pilimit: '10',
    generator: 'random',
    grnnamespace: '0',
    grnlimit: '10'
  }).then(cleanResults).then((x) => { console.log(x); return x })
}

function cleanResults (results) {
  return results.getIn(['query', 'pages'], Map()).toList()
}
