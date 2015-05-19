import {get} from './fetch'
import {memoize} from '../db'
import {Map, List} from 'immutable'

const LIMIT = 15

export default memoize(function (query) {
  return get({
    action: 'query',
    generator: 'prefixsearch',
    gpssearch: query,
    gpsnamespace: 0,
    gpslimit: LIMIT,
    prop: 'pageimages',
    piprop: 'thumbnail',
    pithumbsize: 200,
    pilimit: LIMIT,
    redirects: '',
    list: 'prefixsearch',
    pssearch: query,
    pslimit: LIMIT
  })
  .then(combineResults.bind(null, query))
}, { prefix: 'search-results', refresh: 5 * 60 * 1000 })

function combineResults (query, data) {
  var pages = data.getIn(['query', 'pages'], Map())
  var results = data.getIn(['query', 'prefixsearch'], List())
  return Map({
    query: query,
    results: results.map((result) => result.merge(pages.get(result.pageid, {})))
  })
}
