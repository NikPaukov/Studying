function sleep (time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}
function showForm(formId, id){
    document.getElementById(formId).classList.remove("form-show-anim-reversed");
    document.getElementById(formId).style.display = 'flex';
    document.getElementById(formId).classList.add("form-show-anim");
    document.getElementById("darked").classList.add("darked");
    document.getElementById(`${formId}-id`).value=id;

}
function hidForm(formId){
    document.getElementById(formId).classList.remove("form-show-anim");
    document.getElementById(formId).classList.add("form-show-anim-reversed");
    document.getElementById("darked").classList.remove("darked");
    sleep(500).then(()=>{
        document.getElementById(formId).style.display = 'none';
    })
}
