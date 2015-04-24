export default function (key, value) {
  if (value) window.localStorage.setItem(key, JSON.stringify(value))
  else return JSON.parse(window.localStorage.getItem(key))
}
