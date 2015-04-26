import {EventEmitter} from 'events'
import extend from 'xtend'

export default function (fns) {
  var events = new EventEmitter()
  return extend({
    changed () { events.emit('change') },
    onChange (listener) { events.on('change', listener) },
    offChange (listener) { events.removeListener('change', listener) }
  }, fns)
}
