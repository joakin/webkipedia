import {get} from './fetch'
import {memoize} from './../db'
import {Map, List} from 'immutable'

const LIMIT = 3

export default memoize(function (title) {
  let displayTitle = title.replace(/_/g, ' ')
  return get({
    action: 'query',
    prop: ['extracts', 'pageimages'].join('|'),
    exsentences: '2',
    explaintext: '',
    exlimit: LIMIT,
    piprop: ['thumbnail', 'name', 'original'].join('|'),
    pilimit: LIMIT,
    pithumbsize: 200,
    generator: 'gettingstartedgetpages',
    ggsgptaskname: 'morelike',
    ggsgpexcludedtitle: displayTitle,
    ggsgpcount: LIMIT
  }).then(cleanResults.bind(null, title))
}, { prefix: 'related-articles', refresh: 15 * 60 * 1000 })

function cleanResults (title, data) {
  return Map({
    title: title,
    list: data.getIn(['query', 'pages'], Map()).toList()
  })
}
