import {get} from './fetch'
import {get as getIn} from 'lodash'
import extend from 'xtend'

export default function (query) {
  return get({
    action: 'query',
    generator: 'prefixsearch',
    gpssearch: query,
    gpsnamespace: 0,
    gpslimit: 15,
    prop: 'pageimages',
    piprop: 'thumbnail',
    pithumbsize: 200,
    pilimit: 15,
    redirects: '',
    list: 'prefixsearch',
    pssearch: query,
    pslimit: 15
  }).then(combineResults)
}

function combineResults (data) {
  var pages = getIn(data, 'query.pages', {})
  var results = getIn(data, 'query.prefixsearch', [])
  return results.map((result) =>
    extend(result, pages[result.pageid]))
}
