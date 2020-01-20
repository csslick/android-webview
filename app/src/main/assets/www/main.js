
let btn = document.querySelector('#btn')
btn.addEventListener('click', () => {
    document.querySelector('h1').innerHTML = "Hello JavaScript"
})

let text_input = document.querySelector('#text_input')

text_input.addEventListener('input', () => {
    document.querySelector('h1').innerHTML = text_input.value
})