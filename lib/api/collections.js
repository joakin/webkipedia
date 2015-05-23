import {get} from './fetch'
import {memoize} from './../db'
import {List} from 'immutable'

export function list () {
  return get({
    format: 'json',
    action: 'query',
    list: 'lists',
    lstmode: 'allpublic',
    lstprop: [
      'label',
      'description',
      'public',
      'image',
      'count',
      'updated',
      'owner'
    ].join('|')
  }).then(parseResults)
}// , { prefix: 'articles', refresh: 15 * 60 * 1000 })

function parseResults (data) {
  return data.getIn(['query', 'lists'], List())
}
