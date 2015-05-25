import Immutable from 'immutable'

let storage = {
  get (key) {
    return new Promise((resolve, reject) => {
      resolve(JSON.parse(window.localStorage.getItem(JSON.stringify(key))))
    })
  },
  set (key, value) {
    return new Promise((resolve, reject) => {
      resolve(window.localStorage.setItem(
        JSON.stringify(key), JSON.stringify(value)))
    })
  },
  clear () {
    return new Promise((resolve, reject) => resolve(window.localStorage.clear()))
  }
}

export function set (key, value) {
  return storage.set(key, {
    date: Date.now(),
    value: value.toJS()
  })
}

export function get (key) {
  return storage.get(key).then((entry) => {
    if (entry) {
      return Immutable.fromJS(entry.value)
    } else {
      return null
    }
  })
}

export function memoize (fn, options) {
  let {prefix, refresh} = options
  return function (...args) {
    let key = [prefix, args]
    return get(key).then((entry) => {
      if (entry) {
        if (Date.now() - entry.date > refresh) {
          entry = null
        } else {
          return entry
        }
      }
      if (!entry) {
        return fn.apply(this, args).then((results) => {
          return set(key, results).then(() => results)
        })
      }
    })
  }
}

const VERSION = 4
export function init () {
  return storage.get('webkipedia-version').then(version => {
    if (VERSION !== version) {
      storage.clear()
      return storage.set('webkipedia-version', VERSION)
    }
  })
}
