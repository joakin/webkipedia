// http://en.m.wikipedia.org/w/api.php?action=mobileview&format=json&noheadings=true&page=SM%20U-66&prop=sections|text|lastmodified|lastmodifiedby|languagecount|id|protection|editable|displaytitle|thumb|description|image&sectionprop=toclevel|line|anchor|level|number|fromtitle|index&sections=all&thumbwidth=640
import {get} from './fetch'
import {toArray, memoize} from 'lodash'
import {Map, List} from 'immutable'

export default memoize(function (title) {
  return get({
    format: 'json',
    action: 'mobileview',
    noheadings: true,
    page: title,
    prop: 'sections|text|lastmodified|lastmodifiedby|languagecount|id|protection|editable|displaytitle|thumb|description|image',
    sectionprop: 'toclevel|line|anchor|level|number|fromtitle|index',
    sections: 'all',
    thumbwidth: 640
  }).then(cleanResult).then(addTitle.bind(null, title))
})

function addTitle (title, data) {
  return data.set('title', title)
}

function cleanResult (data) {
  return data.get('mobileview')
}
