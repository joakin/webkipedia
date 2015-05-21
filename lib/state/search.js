import {fromJS} from 'immutable'

export let empty = fromJS({
  query: null,
  results: {query: null, list: []}
})
