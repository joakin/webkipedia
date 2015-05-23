import {fromJS} from 'immutable'

let items = fromJS({
  home: {
    label: 'Home',
    attrs: { href: '#/' }
  },
  explore: {
    label: 'Explore',
    attrs: { href: '#/explore' }
  }
})
export default items
