import immstruct from 'immstruct'
import {empty as emptySearch} from './search'

let state = immstruct({
  route: '',
  search: emptySearch,
  page: {
    title: null,
    content: null
  }
})
export default state
