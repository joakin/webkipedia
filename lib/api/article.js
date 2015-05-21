// http://en.m.wikipedia.org/w/api.php?action=mobileview&format=json&noheadings=true&page=SM%20U-66&prop=sections|text|lastmodified|lastmodifiedby|languagecount|id|protection|editable|displaytitle|thumb|description|image&sectionprop=toclevel|line|anchor|level|number|fromtitle|index&sections=all&thumbwidth=640
import {get} from './fetch'
import {toArray, memoize} from 'lodash'
import {Map, List} from 'immutable'

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
})
// }, { prefix: 'articles', refresh: 15 * 60 * 1000 })

function addTitle (title, data) {
  return data.set('title', title)
}

function cleanResult (data) {
  return data.get('mobileview')
}
