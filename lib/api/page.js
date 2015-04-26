import {get} from './fetch'
import {get as getIn, memoize} from 'lodash'
import extend from 'xtend'

export {featured}
const featured = memoize(function () {
  return get({
    action: 'mobileview',
    format: 'json',
    page: 'Main+Page',
    prop: [
      'text',
      'sections',
      'lastmodified',
      'normalizedtitle',
      'displaytitle',
      'protection',
      'editable'
    ].join('|'),
    onlyrequestedsections: '1',
    sections: 'all',
    sectionprop: 'toclevel|line|anchor',
    noheadings: true
  }).then(extractFeaturedArticle)
})

function extractFeaturedArticle (data) {
  return getIn(data, 'mobileview.sections.0.text', {})
}
