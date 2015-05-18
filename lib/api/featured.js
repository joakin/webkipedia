import {get} from './fetch'
import {toArray, memoize} from 'lodash'
import {Map, List} from 'immutable'

export default memoize(function () {
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
  }).then(parseFeaturedResponse)
})

function parseFeaturedResponse (data) {
  let html = data.getIn(['mobileview', 'sections', 0, 'text'], {})
  return parseHtml(html)
}

function parseHtml (html) {
  let container = document.createElement('div')
  container.innerHTML = html
  let article = parseFeaturedArticleDOM(container.querySelector('#mp-tfa'))
  let news = parseNewsDOM(container.querySelector('#mp-itn'))
  return Map({ article, news })
}

function parseFeaturedArticleDOM (div) {
  let title = div.querySelector('b:first-child a').textContent
  let url = div.querySelector('b:first-child a').href
  let lastp = div.querySelector('p:last-of-type')
  let recentlyFeatured = List(toArray(lastp.querySelectorAll('a')))
    .map((a) => Map({ title: a.textContent, url: a.href }))
  lastp.remove()
  div.querySelector('div:last-of-type').remove()
  let content = div.innerHTML
  return Map({ title, url, content, recentlyFeatured })
}

function parseNewsDOM (div) {
  let imgLink = div.querySelector('div:first-child a')
  let imgTag = imgLink.querySelector('img')
  let image = Map({
    url: imgLink.href,
    title: imgLink.title,
    src: imgTag.src,
    width: imgTag.width,
    height: imgTag.height
  })
  let news = List(toArray(div.querySelectorAll('ul li'))).map(x => x.innerHTML)
  let ongoing = List(toArray(div.querySelectorAll('p:last-of-type a')))
    .map(x => Map({ title: x.title, url: x.href }))
  return Map({ image, news, ongoing })
}
