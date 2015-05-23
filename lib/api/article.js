import {get} from './fetch'
import {memoize} from './../db'
import {List} from 'immutable'

export default memoize(function (title) {
  let displayTitle = title.replace(/_/g, ' ')
  return get({
    format: 'json',
    action: 'mobileview',
    noheadings: true,
    page: displayTitle,
    prop: [
      'sections',
      'text',
      'lastmodified',
      'lastmodifiedby',
      'languagecount',
      'id',
      'protection',
      'editable',
      'displaytitle',
      'thumb',
      'description',
      'image'
    ].join('|'),
    sectionprop: [
      'toclevel',
      'line',
      'anchor',
      'level',
      'number',
      'fromtitle',
      'index'
    ].join('|'),
    sections: 'all',
    thumbwidth: 640
  }).then(cleanResult).then(addTitle.bind(null, title))
}, { prefix: 'articles', refresh: 15 * 60 * 1000 })

function addTitle (title, data) {
  return data.set('title', title)
}

function cleanResult (data) {
  return data.get('mobileview')
}
