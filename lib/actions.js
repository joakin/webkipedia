import {EventEmitter} from 'events'
import {merge} from 'lodash'

export const Actions = merge(new EventEmitter(), {
  SEARCH_QUERY_CHANGED: 'SEARCH_QUERY_CHANGED'
})

export function dispatch (action, ...payload) {
  if (action in Actions) {
    console.log.apply(console, [action].concat(payload))
    Actions.emit.apply(Actions, [action].concat(payload))
  } else {
    throw new Error('Unknown action: ' + action)
  }
}
