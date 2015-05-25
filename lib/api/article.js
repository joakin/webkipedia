import {get} from './fetch'
import {memoize} from './../db'
import {Map, List} from 'immutable'

export default memoize(function (title) {
  let displayTitle = title.replace(/_/g, ' ')
  return get({
    action: 'query',
    prop: ['extracts', 'pageimages'].join('|'),
    exintro: '',
    piprop: ['thumbnail', 'name', 'original'].join('|'),
    pithumbsize: '650',
    titles: displayTitle
  }).then(cleanResult).then(addTitle.bind(null, title))
}, { prefix: 'articles', refresh: 15 * 60 * 1000 })

function addTitle (title, data) {
  return data.set('title', title)
}

function cleanResult (data) {
  return data.getIn(['query', 'pages'], Map()).first()
}
