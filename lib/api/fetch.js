import JSONP from 'browser-jsonp'
import extend from 'xtend'
import Immutable from 'immutable'

const hosts = exports.hosts = {
  en: 'http://en.wikipedia.org/w/api.php'
}

export var host = 'en'

const defaults = {
  format: 'json'
}

export function get (data) {
  return new Promise((resolve, reject) => {
    JSONP({
      url: hosts[host],
      data: extend(defaults, data),
      success: resolve,
      error: reject
    })
  }).then(Immutable.fromJS)
}
