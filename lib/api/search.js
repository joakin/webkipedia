import {get} from './fetch'
import {memoize} from 'lodash'
import Immutable from 'immutable'

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
  .then(combineResults)
})

function combineResults (data) {
  var pages = data.getIn(['query', 'pages'], {})
  var results = data.getIn(['query', 'prefixsearch'], [])
  return results.map((result) =>
    result.merge(pages.get(result.pageid, {})))
}
