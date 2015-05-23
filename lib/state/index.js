import immstruct from 'immstruct'
import {empty as emptySearch} from './search'
import menu from './menu'

let state = immstruct({
  route: '',
  ui: {
    menu: false
  },
  menu: menu,
  search: emptySearch,
  page: {
    title: null,
    content: null
  }
})
export default state
