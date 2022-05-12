'use strict'

//func
function toAnonym(text) {
    const textArr = text.split(/\s+/);
    for(let i = 1; i < textArr.length; i++) {
        if(textArr[i][0].match(/[A-Z]/) && textArr[i-1][textArr[i-1].length-1] != "."){
             if(textArr[i][textArr[i].length-1] =="."){
                 textArr[i] = "writer.";}
            else textArr[i] ="writer"; 
        }
       
    }
    return textArr.join(" ");
}
   
//out
const inpText = `Highly recommend you to read at least one work of Shakespeare. He made his masterpieces for hundreds of years before London, Dickens and Fitzgerald. Hope to buy last book to my collection it 2022, and read it.`
console.log(toAnonym(inpText));
function getParsed(textId, outId) {
    const text = document.getElementById(textId).value;
    document.getElementById(outId).textContent = toAnonym(text);
    }