import Immutable, {List} from 'immutable'
import Cursor from 'immutable/contrib/cursor'

export let initialState = Immutable.fromJS({
  Handler: null,
  router: null,
  searchResults: {query: null, results: []}
})

export function cursor (state, onChange) {
  return Cursor.from(state, newState => {
    if (onChange) onChange(cursor(newState, onChange))
  })
}
